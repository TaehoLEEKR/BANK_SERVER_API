package org.example.domains.history.repository

import com.lecture.bank_server.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface HistoryUserRepository : JpaRepository<User, String>{
    fun findByUlid(ulid : String) : User
}