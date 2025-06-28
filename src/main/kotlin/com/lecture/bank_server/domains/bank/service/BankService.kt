package com.lecture.bank_server.domains.bank.service

import com.github.f4b6a3.ulid.UlidCreator
import com.lecture.bank_server.common.exception.CustomException
import com.lecture.bank_server.common.exception.ErrorCode
import com.lecture.bank_server.common.logging.Logging
import com.lecture.bank_server.common.transaction.Transactional
import com.lecture.bank_server.domains.bank.repository.BankAccountRepository
import com.lecture.bank_server.domains.bank.repository.BankUserRepository
import com.lecture.bank_server.model.dto.Response
import com.lecture.bank_server.model.dto.ResponseProvider
import com.lecture.bank_server.model.entity.Account
import org.slf4j.Logger
import java.lang.Math.random

import org.springframework.stereotype.Service


@Service
class BankService(
    private val transaction : Transactional,
    private val bankUserRepository: BankUserRepository,
    private val bankAccountRepository: BankAccountRepository,
    private val logger : Logger = Logging.getLogger(BankService::class.java)
) {
    fun createAccount(userUlid: String) : Response<String> =Logging.logFor(logger){ log ->
        log["userUlid"] = userUlid
        transaction.run {
            val user = bankUserRepository.findAllByUlid(userUlid) ?: throw CustomException(ErrorCode.NOT_FOUND_USER)
            val ulid = UlidCreator.getUlid().toString()
            val accountNumber = generateRandomAccountNumber()

            val account = Account(
                ulid = ulid,
                user = user,
                accountNumber = accountNumber,
            )

            try{
                bankAccountRepository.save(account)
            }catch (e: Exception){
                throw CustomException(ErrorCode.FAILED_TO_SAVE_DATA, e.message ?: "Failed to save data")
            }
        }


        return@logFor ResponseProvider.success("SUCCESS")
    }

    fun balance(userUlid: String, accountUlid: String) : Response<String> = Logging.logFor(logger) { log ->
        log["userUlid"] = userUlid
        log["accountUlid"] = accountUlid
        return@logFor transaction.run {
            val account = bankAccountRepository.findAllByUlid(accountUlid) ?: throw CustomException(ErrorCode.FAILED_TO_FIND_ACCOUNT,accountUlid)
            if(account.user.ulid != userUlid) throw CustomException(ErrorCode.MISS_MATCH_ACCOUNT_ULID_AND_USER_ULID)
            ResponseProvider.success(account.balance.toString())
        }
    }

    fun removeAccount(userUlid: String, accountUlid: String) : Response<String> = Logging.logFor(logger){ log ->
        log["userUlid"] = userUlid
        log["accountUlid"] = accountUlid
        transaction.run { }
        return@logFor ResponseProvider.success("SUCCESS")
    }

    private fun generateRandomAccountNumber() : String {
        val bankCode = "003"
        val section = "12"

        val number = random().toString()

        return "$bankCode-$section-$number"
    }
}