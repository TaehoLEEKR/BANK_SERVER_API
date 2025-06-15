package com.lecture.bank_server.domains.auth.service

import com.lecture.bank_server.common.exception.CustomException
import com.lecture.bank_server.common.exception.ErrorCode
import com.lecture.bank_server.common.httpClient.CallClient
import com.lecture.bank_server.common.json.JsonUtil
import com.lecture.bank_server.config.OAuth2Config
import com.lecture.bank_server.interfaces.OAuth2TokenResponse
import com.lecture.bank_server.interfaces.OAuth2UserResponse
import com.lecture.bank_server.interfaces.OAuthServiceInterface
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import okhttp3.FormBody
import org.springframework.stereotype.Service

private const val key = "google"

@Service(key)
class GoogleAuthService(
    private val config : OAuth2Config,
    private val httpClient: CallClient,
) : OAuthServiceInterface{

    private val oAuthInfo = config.providers[key]   ?: throw CustomException(ErrorCode.AUTH_CONFIG_NOT_FOUND, key)
    private val tokenURL = "https://oauth2.googleapis.com/token"
    private val userInfoURL = "https://www.googleapis.com/oauth2/v3/userinfo"

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

        val response : GoogleTokenResponse = JsonUtil.decodeToJson(jsonString,GoogleTokenResponse.serializer())
        return response

    }

    override fun getUserInfo(accessToken: String): OAuth2UserResponse {
        val headers = mapOf(
            "Content-Type" to "application/json",
            "Authorization" to "Bearer $accessToken"
        )
        val jsonString = httpClient.GET(userInfoURL,headers)
        val response : GoogleUserResponse = JsonUtil.decodeToJson(jsonString,GoogleUserResponse.serializer())
        return response
    }

}

@Serializable
data class GoogleTokenResponse(
    @SerialName("access_token") override val accessToken: String
) : OAuth2TokenResponse


@Serializable
data class GoogleUserResponse(
    override val id: String
    , override val name: String
    , override val email: String,
): OAuth2UserResponse
