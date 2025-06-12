package com.lecture.bank_server.domains.auth.service

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
    override val providerName: String
        get() = TODO("Not yet implemented")

    override fun getAccessToken(code: String): OAuth2TokenResponse {
        TODO("Not yet implemented")
    }

    override fun getUserInfo(accessToken: String): OAuth2UserResponse {
        TODO("Not yet implemented")
    }
}