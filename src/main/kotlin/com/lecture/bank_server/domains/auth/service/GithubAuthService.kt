package com.lecture.bank_server.domains.auth.service

import com.lecture.bank_server.common.exception.CustomException
import com.lecture.bank_server.common.exception.ErrorCode
import com.lecture.bank_server.common.httpClient.CallClient
import com.lecture.bank_server.config.OAuth2Config
import com.lecture.bank_server.interfaces.OAuth2TokenResponse
import com.lecture.bank_server.interfaces.OAuth2UserResponse
import com.lecture.bank_server.interfaces.OAuthServiceInterface
import okhttp3.FormBody
import okhttp3.OkHttpClient
import org.springframework.stereotype.Service

private const val key = "github"

@Service(key)
class GithubAuthService(
    private val config: OAuth2Config,
    private val httpClient: CallClient,
) : OAuthServiceInterface {

    private val oAuthInfo = config.providers[key]   ?: throw CustomException(ErrorCode.AUTH_CONFIG_NOT_FOUND, key)
    private val tokenURL = "https://github.com/login/oauth/access_token"
    private val userInfoURL = "https://api.github.com/user"
    override val providerName: String = key

    override fun getAccessToken(code: String): OAuth2TokenResponse {
        val body = FormBody.Builder()
            .add("code",code)
            .add("client_id",oAuthInfo.clientId)
            .add("client_secret",oAuthInfo.clientSecret)
            .add("redirect_uri",oAuthInfo.redirectUri)
            .build()

        val headers = mapOf("Accept" to "application/json")
        val jsonString =  httpClient.POST(tokenURL,headers,body)

        TODO()
    }

    override fun getUserInfo(accessToken: String): OAuth2UserResponse {


        TODO("Not yet implemented")
    }
}