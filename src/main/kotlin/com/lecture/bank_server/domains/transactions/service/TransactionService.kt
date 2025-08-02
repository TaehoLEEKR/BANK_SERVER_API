package com.lecture.bank_server.domains.transactions.service

import com.lecture.bank_server.common.cache.RedisClient
import com.lecture.bank_server.common.cache.RedisKeyProvider
import com.lecture.bank_server.common.exception.CustomException
import com.lecture.bank_server.common.exception.ErrorCode
import com.lecture.bank_server.common.logging.Logging
import com.lecture.bank_server.common.logging.Logging.logFor
import com.lecture.bank_server.common.transaction.Transactional
import com.lecture.bank_server.domains.transactions.domain.DepositResponse
import com.lecture.bank_server.domains.transactions.domain.TransferResponse
import com.lecture.bank_server.domains.transactions.repository.TransactionsAccount
import com.lecture.bank_server.domains.transactions.repository.TransactionsUser
import com.lecture.bank_server.model.dto.Response
import com.lecture.bank_server.model.dto.ResponseProvider
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

    fun deposit(userUlid: String, accountID : String, value : BigDecimal) : Response<DepositResponse> = Logging.logFor(logger) { it

        it["AccountID"] = accountID
        it["Ulid"] = userUlid
        it["Value"] = value


        val key = RedisKeyProvider.bankMutexKey(userUlid,accountID)

        return@logFor redisClient.invokeWithMutex(key){
            return@invokeWithMutex transactional.run {
                val user = transactionsUser.findByUlid(userUlid)

                val account = transactionsAccount.findByUlidAndUser(accountID, user)
                    ?: throw CustomException(ErrorCode.FAILED_TO_FIND_ACCOUNT)

                account.balance = account.balance.add(value)
                account.updatedAt = LocalDateTime.now()
                transactionsAccount.save(account)

                ResponseProvider.success(DepositResponse(afterBalance = account.balance) )
            }
        }


    }

    fun transfer(fromUlid : String , fromAccountId: String, toAccountId: String, value: BigDecimal) : Response<TransferResponse> = Logging.logFor(logger){ it

        it["fromUlid"] = fromUlid
        it["fromAccountId"] = fromAccountId
        it["toUlid"] = toAccountId
        it["Value"] = value

        val key = RedisKeyProvider.bankMutexKey(fromUlid, fromAccountId)

        return@logFor redisClient.invokeWithMutex(key){
            return@invokeWithMutex transactional.run {
                val fromAccount = transactionsAccount.findByUlid(fromAccountId)
                    ?:  throw CustomException(ErrorCode.FAILED_TO_FIND_ACCOUNT)


                if(fromAccount.user.ulid != fromUlid){
                    throw CustomException(ErrorCode.MISS_MATCH_ACCOUNT_ULID_AND_USER_ULID)
                }else if( fromAccount.balance < value ){
                    throw CustomException(ErrorCode.ENOUGH_VALUE)
                }else if(value <= BigDecimal.ZERO){
                    throw CustomException(ErrorCode.FAILED_TO_FIND_ACCOUNT)
                }

                val toAccount = transactionsAccount.findByUlid(toAccountId)
                    ?: throw CustomException(ErrorCode.FAILED_TO_FIND_ACCOUNT)

                fromAccount.balance = fromAccount.balance.subtract(value)
                toAccount.balance = toAccount.balance.add(value)

                transactionsAccount.save(toAccount)
                transactionsAccount.save(fromAccount)

                ResponseProvider.success(TransferResponse(
                    afterBalance = fromAccount.balance,
                    afterToBalance = toAccount.balance,
                ))
            }
        }

    }

}