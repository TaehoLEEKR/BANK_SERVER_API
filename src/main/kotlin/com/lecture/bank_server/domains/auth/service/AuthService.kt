package com.lecture.bank_server.domains.auth.service

import com.lecture.bank_server.common.exception.CustomException
import com.lecture.bank_server.common.exception.ErrorCode
import com.lecture.bank_server.common.jwt.JwtProvider
import com.lecture.bank_server.common.jwt.jwtProvider
import com.lecture.bank_server.interfaces.OAuthServiceInterface
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val oAuth2Services : Map<String,OAuthServiceInterface>
    private val jwtProvider: JwtProvider
) {
    fun handleAuth(state: String, code: String) : String{
        val provider = state.lowercase()

        val callService = oAuth2Services[provider] ?: throw CustomException(ErrorCode.NOT_FOUND_PROVIDER)

        val accessToken = callService.getAccessToken(code)

        val userInfo = callService.getUserInfo(accessToken.accessToken)

        val token = jwtProvider.createToken(provider, userInfo.email, userInfo.name, userInfo.id)
        //userInfo

    }
}