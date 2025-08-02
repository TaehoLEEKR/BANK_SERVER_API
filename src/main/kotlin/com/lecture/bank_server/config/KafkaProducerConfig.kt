package com.lecture.bank_server.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.core.KafkaTemplate

@Configuration
class KafkaProducerConfig(
    @Value("\${spring.kafka.bootstrap-servers}") private val servers: String,
) {

    @Bean
    fun producerFactory() : ProducerFactory<String, Any>{
        val props = HashMap<String, Any>()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = servers
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        return DefaultKafkaProducerFactory(props)
    }

    @Bean
    fun kafkaTemplate() : KafkaTemplate<String,Any> {
        return KafkaTemplate(producerFactory())
    }

}