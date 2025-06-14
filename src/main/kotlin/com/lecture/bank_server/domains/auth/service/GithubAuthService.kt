package com.lecture.bank_server.domains.auth.service

import com.lecture.bank_server.common.exception.CustomException
import com.lecture.bank_server.common.exception.ErrorCode
import com.lecture.bank_server.config.OAuth2Config
import com.lecture.bank_server.interfaces.OAuth2TokenResponse
import com.lecture.bank_server.interfaces.OAuth2UserResponse
import com.lecture.bank_server.interfaces.OAuthServiceInterface
import org.springframework.stereotype.Service

private const val key = "github"

@Service(key)
class GithubAuthService(
    private val config : OAuth2Config
) : OAuthServiceInterface {

    private val oAuthInfo = config.providers[key]   ?: throw CustomException(ErrorCode.AUTH_CONFIG_NOT_FOUND, key)

    override val providerName: String = key

    override fun getAccessToken(code: String): OAuth2TokenResponse {
        TODO("Not yet implemented")
    }

    override fun getUserInfo(accessToken: String): OAuth2UserResponse {
        TODO("Not yet implemented")
    }
}