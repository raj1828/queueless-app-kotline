package com.example.queueless.data.remote.dto.auth

data class ApiResponse<T>(
    val message: String,
    val userId: String,
    val success: Boolean
)