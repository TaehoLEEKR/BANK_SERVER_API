package org.example.types.entity

import com.lecture.bank_server.common.json.BigDecimalSerialization
import com.lecture.bank_server.common.json.LocalDateTimeSerialization
import kotlinx.serialization.Serializable
import org.example.types.dto.History
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
@Document(collection = "transaction_history")
class TransactionHistoryDocument(
    val fromUlid : String,
    val toUlid : String,

    @Serializable(with = BigDecimalSerialization::class)
    val value : BigDecimal,
    @Serializable(with = LocalDateTimeSerialization::class)
    val time : LocalDateTime
) {

    fun toHistory(fromUser : String, toUser: String) : History = History(
        fromUser = fromUser,
        toUser = toUser,
        fromUlid = fromUlid,
        toUlid = toUlid,
        value = value,
        time = time
    )

}