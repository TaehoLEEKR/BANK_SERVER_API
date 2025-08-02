package com.lecture.bank_server.domains.bank.repository

import com.lecture.bank_server.model.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface BankAccountRepository : JpaRepository<Account,String> {
    fun findAllByUlid(Ulid: String): Account?
}