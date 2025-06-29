package com.lecture.bank_server.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "account")
data class Account(

    // 시간 값을 가진 id를 생성
    // 보안적인 이슈로도 좋다.
    @Id
    @Column(name="ulid", length = 12, nullable = false)
    val ulid: String,

    // Todo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_ulid", nullable = false)
    val user: User,

    @Column(name = "balance", nullable = false, precision = 15, scale = 2)
    var balance: BigDecimal = BigDecimal.ZERO,

    @Column(name = "account_number", length = 100, nullable = false, unique = true)
    val accountNumber: String,

    @Column(name = "is_deleted", nullable = false)
    val isDeleted: Boolean = false,


    @Column(name="created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name="updated_at", nullable = false, updatable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name="deleted_at")
    val deletedAt: LocalDateTime? = null,

)