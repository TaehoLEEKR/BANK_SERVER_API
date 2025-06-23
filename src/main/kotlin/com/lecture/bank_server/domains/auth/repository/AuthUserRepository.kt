package com.lecture.bank_server.domains.auth.repository

import com.lecture.bank_server.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface AuthUserRepository : JpaRepository<User, String>{
    fun existsByUsername(username: String): Boolean

    @Modifying
    @Query("UPDATE User SET accessToken = :accessToken WHERE username = :username")
    fun updateAccessTokenByUsername(username: String, accessToken: String)
}