package com.lecture.bank_server.domains.transactions.model

import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class DepositRequest(
    @field:NotBlank(message = "Name cannot be blank")
    val toAccountId: String,

    @field:NotBlank(message = "Name cannot be blank")
    val toUlid: String,

    @field:NotBlank(message = "Name cannot be blank")
    val value: BigDecimal
)

data class TransferRequest(
    @field:NotBlank(message = "Name cannot be blank")
    val fromAccountId: String,

    @field:NotBlank(message = "Name cannot be blank")
    val toAccountId: String,

    @field:NotBlank(message = "Name cannot be blank")
    val fromUlid: String,

    @field:NotBlank(message = "Name cannot be blank")
    val value: BigDecimal
)