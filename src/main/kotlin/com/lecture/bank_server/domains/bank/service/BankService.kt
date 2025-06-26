package com.lecture.bank_server.domains.bank.service

import com.lecture.bank_server.common.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class BankService(
    private val transaction : Transactional
) {
    fun createAccount(){

    }

    fun balance(){

    }

    fun removeAccount(){

    }
}