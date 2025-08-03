package com.lecture.bank_server.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import java.time.Duration

@Configuration
class RedisConfig {

    @Bean
    fun redisConnectionFactory(
        @Value("\${database.redis.host}")   host : String,
        @Value("\${database.redis.port}")   port : Int,
        @Value("\${database.redis.password:}")   password : String?,
        @Value("\${database.redis.database:0}")   database : Int,
        @Value("\${database.redis.timeout:10000}")   timeout : Long,
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

    @Bean
    @Primary
    fun redisTemplate(connectionFactory: RedisConnectionFactory) : RedisTemplate<String,String>{

        val template = RedisTemplate<String,String>()

        template.connectionFactory = connectionFactory
        template.keySerializer = template.stringSerializer
        template.valueSerializer = Jackson2JsonRedisSerializer(String::class.java)
        template.hashKeySerializer = template.stringSerializer
        template.hashValueSerializer =  Jackson2JsonRedisSerializer(String::class.java)
        template.afterPropertiesSet()

        return template
    }

    @Bean
    fun redissonClient(
        @Value("\${database.redisson.host}")   host : String,  // redisson.host를 사용 (redis://127.0.0.1:6379 형식)
        @Value("\${database.redisson.timeout:10000}")   timeout : Int,
        @Value("\${database.redisson.password:}")   password : String?,
    ) : RedissonClient {
        val config = Config()

        val singleServer = config.useSingleServer()
            .setAddress(host)  // 이미 "redis://127.0.0.1:6379" 형식
            .setTimeout(timeout)

        if(!password.isNullOrBlank()){
            singleServer.setPassword(password)
        }
        return Redisson.create(config).also {
            println("redisson create success")
        }
    }
}