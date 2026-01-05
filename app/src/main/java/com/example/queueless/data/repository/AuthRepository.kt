package com.example.queueless.data.repository

import com.example.queueless.data.remote.RetrofitClient
import com.example.queueless.data.remote.auth.AuthApi
import com.example.queueless.data.remote.dto.auth.ApiResponse
import com.example.queueless.data.remote.dto.auth.AuthTokenResponse
import com.example.queueless.data.remote.dto.auth.LoginRequest
import com.example.queueless.data.remote.dto.auth.RegisterRequest
import com.example.queueless.data.remote.dto.auth.VerifyOtpRequest

class AuthRepository {
    private  val api = RetrofitClient
        .retrofit
        .create(AuthApi::class.java)

    suspend fun register(
        name: String,
        email: String,
        password: String,
        user: String
    ): ApiResponse<Unit>{
        return  api.register(
            RegisterRequest(name, email, password, user)
        )
    }

    suspend fun login(
        email: String,
        password: String
    ): ApiResponse<Unit> {
        return  api.login(
            LoginRequest(email, password)
        )
    }

    suspend fun verifyRegisterOtp(
        userId: String,
        otp: String
    ): AuthTokenResponse{
        return  api.verifyRegisterOtp(
            VerifyOtpRequest(otp, userId)
        )
    }
    suspend fun verifyLoginOtp(
        userId: String,
        otp: String
    ): AuthTokenResponse{
        return  api.verifyLoginOtp(
            VerifyOtpRequest(otp, userId)
        )
    }
}