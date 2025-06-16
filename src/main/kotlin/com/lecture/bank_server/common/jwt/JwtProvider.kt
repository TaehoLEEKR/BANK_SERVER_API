package com.lecture.bank_server.common.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtProvider (
    @Value("\${jwt.secret-key}") private val secretKey : String,
    @Value("\${jwt.time}") private val time : Long
){

    private val ONE_MINUTE_TO_MILLIS : Long = 60 * 1000

    fun createToken(platform: String, email: String? , name :String? ,id: String) : String {
        return JWT.create()
            .withSubject("$platform - $email - $name - $id")
            .withIssuedAt(Date())
            .withExpiresAt(Date(System.currentTimeMillis() + time * ONE_MINUTE_TO_MILLIS))
            .sign(Algorithm.HMAC256(secretKey))

    }


}