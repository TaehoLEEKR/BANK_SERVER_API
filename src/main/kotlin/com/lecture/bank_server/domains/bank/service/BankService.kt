package com.lecture.bank_server.domains.bank.service

import com.lecture.bank_server.common.logging.Logging
import com.lecture.bank_server.common.transaction.Transactional
import com.lecture.bank_server.domains.bank.repository.BankAccountRepository
import com.lecture.bank_server.domains.bank.repository.BankUserRepository
import com.lecture.bank_server.model.dto.Response
import com.lecture.bank_server.model.dto.ResponseProvider
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
        transaction.run { }


        return@logFor ResponseProvider.success("SUCCESS")
    }

    fun balance(userUlid: String, accountUlid: String) : Response<String> = Logging.logFor(logger) { log ->
        log["userUlid"] = userUlid
        log["accountUlid"] = accountUlid
        transaction.run { }
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