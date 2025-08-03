package com.lecture.bank_server.message

import com.lecture.bank_server.common.exception.CustomException
import com.lecture.bank_server.common.exception.ErrorCode
import com.lecture.bank_server.common.logging.Logging
import org.apache.kafka.clients.producer.KafkaProducer
import org.slf4j.Logger
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

enum class Topic(val topic: String) {
    Transactions("transactions")
}


@Component
class kafkaProducer(
    private val template : KafkaTemplate<String,Any>,
    private val log: Logger = Logging.getLogger(KafkaProducer::class.java)
) {
    fun sendMessage(topic: String, message: String) {

        val future = template.send(topic, message)

        future.whenComplete { result, ex ->
            if (ex == null) {
                log.info("Sent message=[$message] with offset=[${result.recordMetadata.offset()}]")
            } else {
                log.error("Unable to send message=[$message] due to : ${ex.message}")
                throw CustomException(ErrorCode.FAILED_TO_SEND_MESSAGE, ex.message ?: "Unknown Error")
            }
        }
    }
}