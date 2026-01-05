package com.example.queueless.data.remote.auth

import com.example.queueless.data.remote.dto.auth.ApiResponse
import com.example.queueless.data.remote.dto.auth.AuthTokenResponse
import com.example.queueless.data.remote.dto.auth.LoginRequest
import com.example.queueless.data.remote.dto.auth.RegisterRequest
import com.example.queueless.data.remote.dto.auth.VerifyOtpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): ApiResponse<Unit>

    @POST("api/auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): ApiResponse<Unit>

    @POST("api/auth/login/verify-otp")
    suspend fun verifyLoginOtp(
        @Body request: VerifyOtpRequest
    ): AuthTokenResponse

    @POST("api/auth/register/verify-otp")
    suspend fun verifyRegisterOtp(
        @Body request: VerifyOtpRequest
    ): AuthTokenResponse
}