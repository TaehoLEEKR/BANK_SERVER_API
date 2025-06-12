package com.lecture.bank_server.domains.auth.service

import com.lecture.bank_server.config.OAuth2Config
import org.springframework.stereotype.Service

private const val key = "google"

@Service(key)
class GoogleAuthService(
    private val config : OAuth2Config
) {
}