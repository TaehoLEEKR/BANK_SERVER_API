package com.lecture.bank_server.domains.bank.repository

import com.lecture.bank_server.model.entity.Account
import com.lecture.bank_server.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface BankUserRepository : JpaRepository<User, String> {
    fun findAllByUlid(Ulid: String): User?
}