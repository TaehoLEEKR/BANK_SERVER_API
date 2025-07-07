package com.lecture.bank_server.domains.transactions.service

import com.lecture.bank_server.common.cache.RedisClient
import com.lecture.bank_server.common.cache.RedisKeyProvider
import com.lecture.bank_server.common.exception.CustomException
import com.lecture.bank_server.common.exception.ErrorCode
import com.lecture.bank_server.common.logging.Logging
import com.lecture.bank_server.common.transaction.Transactional
import com.lecture.bank_server.domains.transactions.repository.TransactionsAccount
import com.lecture.bank_server.domains.transactions.repository.TransactionsUser
import org.slf4j.Logger
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class TransactionService (
    private val transactionsUser: TransactionsUser,
    private val transactionsAccount: TransactionsAccount,
    private val redisClient: RedisClient,
    private val transactional: Transactional,
    private val logger : Logger = Logging.getLogger(TransactionService::class.java)
){

    fun deposit(userUlid: String, accountID : String, value : BigDecimal) = Logging.logFor(logger) { it

        it["AccountID"] = accountID
        it["Ulid"] = userUlid
        it["Value"] = value


        val key = RedisKeyProvider.bankMutexKey(userUlid,accountID)

        redisClient.invokeWithMutex(key){
            return@invokeWithMutex transactional.run {
                val user = transactionsUser.findByUlid(userUlid)

                val account = transactionsAccount.findByUlidAndUser(accountID, user)
                    ?: throw CustomException(ErrorCode.FAILED_TO_FIND_ACCOUNT)

                account.balance = account.balance.add(value)
                account.updatedAt = LocalDateTime.now()
                transactionsAccount.save(account)

            }
        }


    }
}