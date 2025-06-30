package com.lecture.bank_server.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory

@Configuration
class RedisConfig {

    @Bean
    fun redisConnectionFactory(
        @Value("\${database.redis.host}")   host : String,
        @Value("\${database.redis.port}")   port : String,
        @Value("\${database.redis.password:${null}}")   password : String,
        @Value("\${database.redis.database:${0}}")   database : String,
        @Value("\${database.redis.timeout:${10000}}")   timeout : String,
        ) : LettuceConnectionFactory {

    }
}