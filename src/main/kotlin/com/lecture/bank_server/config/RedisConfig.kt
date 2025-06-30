package com.lecture.bank_server.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import java.time.Duration

@Configuration
class RedisConfig {

    @Bean
    fun redisConnectionFactory(
        @Value("\${database.redis.host}")   host : String,
        @Value("\${database.redis.port}")   port : Int,
        @Value("\${database.redis.password:${null}}")   password : String?,
        @Value("\${database.redis.database:${0}}")   database : Int,
        @Value("\${database.redis.timeout:${10000}}")   timeout : Long,
    ) : LettuceConnectionFactory {
        val config = RedisStandaloneConfiguration(host, port).apply {
            password?.let { this.setPassword(it) }
            setDatabase(database)
        }

        val clientConfig = LettuceClientConfiguration.builder()
            .commandTimeout(Duration.ofMillis(timeout))
            .build()

        return LettuceConnectionFactory(config, clientConfig)
    }
}