package com.lecture.bank_server.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "oauth2")
class OAuth2Config {
    val providers: MutableMap<String, OAuth2ProviderValues> = mutableMapOf()
}


data class OAuth2ProviderValues(
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String,
)