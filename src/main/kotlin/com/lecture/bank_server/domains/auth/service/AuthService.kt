package com.lecture.bank_server.domains.auth.service

import com.lecture.bank_server.common.exception.CustomException
import com.lecture.bank_server.common.exception.ErrorCode
import com.lecture.bank_server.common.jwt.JwtProvider
import com.lecture.bank_server.common.logging.Logging
import com.lecture.bank_server.common.transaction.Transactional
import com.lecture.bank_server.interfaces.OAuthServiceInterface
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val oAuth2Services : Map<String,OAuthServiceInterface>,
    private val jwtProvider: JwtProvider,
    private val transaction : Transactional,
) {
    private val logger: Logger = Logging.getLogger(AuthService::class.java)


    fun handleAuth(state: String, code: String) : String = Logging.logFor(logger) { log ->
        val provider = state.lowercase()

        log["provider"] = provider

        val callService = oAuth2Services[provider] ?: throw CustomException(ErrorCode.NOT_FOUND_PROVIDER)

        val accessToken = callService.getAccessToken(code)

        val userInfo = callService.getUserInfo(accessToken.accessToken)

        val token = jwtProvider.createToken(provider, userInfo.email, userInfo.name, userInfo.id)
        //userInfo

//        transaction.run {
//
//        }

    }
}