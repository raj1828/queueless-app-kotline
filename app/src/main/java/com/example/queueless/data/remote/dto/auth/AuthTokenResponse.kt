package com.example.queueless.data.remote.dto.auth

data class AuthTokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val role: String
)