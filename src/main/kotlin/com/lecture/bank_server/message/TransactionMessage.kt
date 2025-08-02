package com.lecture.bank_server.message

import com.lecture.bank_server.common.json.BigDecimalSerialization
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class TransactionMessage(

    val fromUlid : String,

    val fromName : String,
    val fromAccountId : String,

    val toUlid : String,

    val toName : String,
    val toAccountId : String,

    @Serializable(with = BigDecimalSerialization::class)
    val value : BigDecimal,

    @Serializable(with = BigDecimalSerialization::class)
    var time : LocalDateTime = LocalDateTime.now()
)