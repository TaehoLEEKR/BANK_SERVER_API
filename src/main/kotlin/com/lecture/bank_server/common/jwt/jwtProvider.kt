package com.lecture.bank_server.common.jwt

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class jwtProvider(
    @Value("\${jwt.sevret-key}") private val secretKey : String,
    @Value("\${jwt.time}") private val time : Long,
    ) {

    fun createToken(platform : String , email : String , name : String , id: String) : String {
        return OAuth2ResoruceServerProperties.jwt
    }
}