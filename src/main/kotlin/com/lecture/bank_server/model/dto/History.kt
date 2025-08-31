package org.example.types.dto


import com.lecture.bank_server.common.json.BigDecimalSerialization
import com.lecture.bank_server.common.json.LocalDateTimeSerialization
import kotlinx.serialization.Serializable
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Serializable
data class History(
    val fromUlid : String,
    val fromUser : String,

    val toUlid : String,
    val toUser : String,

    @Serializable(with = BigDecimalSerialization::class)
    val value : BigDecimal,
    @Serializable(with = LocalDateTimeSerialization::class)
    val time : LocalDateTime
)