package com.lecture.bank_server.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User (

    @Id
    @Column(name="ulid", length = 12, nullable = false)
    val ulid: String,

    @Column(name = "username", length = 50, nullable = false, unique = true)
    val username: String,

    @Column(name = "access_token", length = 255)
    val accessToken: String? = null,


    @Column(name="created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "user")
    val accounts: List<Account> = mutableListOf()
)