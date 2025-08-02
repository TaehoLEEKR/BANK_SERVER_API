package com.lecture.bank_server.domains.transactions.repository

import com.lecture.bank_server.model.entity.Account
import com.lecture.bank_server.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionsAccount : JpaRepository<Account, String> {
    fun findByUlidAndUser(uuid: String, user: User): Account?
    fun findByUlid(uuid: String): Account?
}