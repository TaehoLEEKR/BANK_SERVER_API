package com.lecture.bank_server.interfaces

interface OAuthServiceInterface {
    val providerName: String
    fun getAccessToken(code: String) : OAuth2TokenResponse
    fun getUserInfo(accessToken: String) : OAuth2UserResponse
}

interface OAuth2TokenResponse{
    val accessToken: String
}

interface OAuth2UserResponse{
    val id: String
    val email: String?
    val name: String?
}