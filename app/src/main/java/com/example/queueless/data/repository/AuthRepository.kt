package com.example.queueless.data.repository

import com.example.queueless.data.local.dao.AuthDao
import com.example.queueless.data.local.entity.AuthEntity
import com.example.queueless.data.remote.auth.AuthApi
import com.example.queueless.data.remote.dto.auth.*
import retrofit2.Retrofit

class AuthRepository(
    private val authDao: AuthDao,
    retrofit: Retrofit          // 👈 INJECT RETROFIT
) {

    private val api = retrofit.create(AuthApi::class.java)

    fun observeAuth() = authDao.getAuth()

    suspend fun register(
        name: String,
        email: String,
        password: String,
        user: String
    ): ApiResponse<Unit> {
        return api.register(
            RegisterRequest(name, email, password, user)
        )
    }

    suspend fun login(
        email: String,
        password: String
    ): ApiResponse<Unit> {
        return api.login(
            LoginRequest(email, password)
        )
    }

    suspend fun verifyRegisterOtp(
        userId: String,
        otp: String
    ): AuthTokenResponse {
        return api.verifyRegisterOtp(
            VerifyOtpRequest(otp, userId)
        )
    }

    suspend fun verifyLoginOtp(
        userId: String,
        otp: String
    ): AuthTokenResponse {
        return api.verifyLoginOtp(
            VerifyOtpRequest(otp, userId)
        )
    }

    suspend fun saveSession(
        accessToken: String,
        refreshToken: String
    ) {
        authDao.saveAuth(
            AuthEntity(
                accessToken = accessToken,
                refreshToken = refreshToken,
                isAuthenticated = true
            )
        )
    }

    suspend fun clearSession() {
        authDao.clearAuth()
    }
}
