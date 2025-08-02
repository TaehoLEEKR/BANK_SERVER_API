package com.lecture.bank_server.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProducerConfig(
    @Value("\${spring.kafka.bootstrap-servers}") private val servers: String,
) {

}