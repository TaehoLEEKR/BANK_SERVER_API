package com.lecture.bank_server.domains.transactions.repository

import com.lecture.bank_server.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionsUser : JpaRepository<User,String> {
    fun findByUlid(uuid: String): User
}