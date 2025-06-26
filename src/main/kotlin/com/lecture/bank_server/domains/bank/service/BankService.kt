package com.lecture.bank_server.domains.bank.service

import com.lecture.bank_server.common.logging.Logging
import com.lecture.bank_server.common.transaction.Transactional
import org.slf4j.Logger

import org.springframework.stereotype.Service

@Service
class BankService(
    private val transaction : Transactional,
    private val logger : Logger = Logging.getLogger(BankService::class.java)
) {
    fun createAccount(userUlid: String)=Logging.logFor(logger){ log ->
        log["userUlid"] = userUlid
        transaction.run { }
    }

    fun balance(userUlid: String, accountUlid: String) = Logging.logFor(logger) { log ->
        log["userUlid"] = userUlid
        log["accountUlid"] = accountUlid
        transaction.run { }
    }

    fun removeAccount(userUlid: String, accountUlid: String) = Logging.logFor(logger){ log ->
        log["userUlid"] = userUlid
        log["accountUlid"] = accountUlid
        transaction.run { }
    }
}