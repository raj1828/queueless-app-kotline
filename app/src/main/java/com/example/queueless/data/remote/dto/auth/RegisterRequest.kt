package com.example.queueless.data.remote.dto.auth

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val role: String
)