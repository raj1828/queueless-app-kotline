package com.example.queueless.data.remote.dto.auth

data class VerifyOtpRequest(
    val otp: String,
    val userId: String
)