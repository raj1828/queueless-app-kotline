package com.example.queueless.ui.auth.AuthViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.queueless.data.local.TokenDataStore
import com.example.queueless.data.repository.AuthRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AuthViewModel(
    private val tokenStore: TokenDataStore,
    private val repository: AuthRepository
) : ViewModel() {

    /* ---------------- EXISTING STATES (UNCHANGED) ---------------- */

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess

    private val _registerSuccess = MutableStateFlow(false)
    val registerSuccess: StateFlow<Boolean> = _registerSuccess

    /* ---------------- ROOM AUTH STATE ---------------- */

    /**
     * 🔐 TRUE  -> user already logged in (skip login)
     * 🔓 FALSE -> user must login
     */
    val isLoggedIn: StateFlow<Boolean> =
        repository.observeAuth()
            .map { auth ->
                auth?.isAuthenticated == true
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false
            )

    /* ---------------- INTERNAL (NOT STATE) ---------------- */

    // userId from backend (used for OTP verification)
    private var userId: String? = null

    // true = register flow, false = login flow
    private var isRegisterFlow: Boolean = false

    /* ---------------- REGISTER ---------------- */

    fun register(
        name: String,
        email: String,
        password: String,
        role: String
    ) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val response = repository.register(
                    name,
                    email,
                    password,
                    role
                )

                if (response.message == "Registration successful. Please verify OTP.") {
                    userId = response.userId
                    isRegisterFlow = true
                    _registerSuccess.value = true
                } else {
                    _error.value = response.message
                }

            } catch (e: Exception) {
                _error.value = e.message
            }

            _loading.value = false
        }
    }

    /* ---------------- LOGIN ---------------- */

    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val response = repository.login(
                    email = email,
                    password = password
                )

                if (response.message == "OTP sent for login verification") {
                    userId = response.userId
                    isRegisterFlow = false
                    _loginSuccess.value = true
                } else {
                    _error.value = response.message
                }

            } catch (e: Exception) {
                _error.value = e.message
            }

            _loading.value = false
        }
    }

    /* ---------------- OTP VERIFY (REGISTER + LOGIN) ---------------- */

    fun verifyOtp(otp: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val uid = userId ?: throw IllegalStateException("UserId not found")

                val tokenResponse =
                    if (isRegisterFlow) {
                        repository.verifyRegisterOtp(
                            userId = uid,
                            otp = otp
                        )
                    } else {
                        repository.verifyLoginOtp(
                            userId = uid,
                            otp = otp
                        )
                    }

                // ✅ SAVE SESSION IN ROOM
                repository.saveSession(
                    accessToken = tokenResponse.accessToken,
                    refreshToken = tokenResponse.refreshToken
                )

                // (Optional) also save in DataStore if you still want
                tokenStore.saveTokens(
                    access = tokenResponse.accessToken,
                    refresh = tokenResponse.refreshToken
                )

            } catch (e: Exception) {
                _error.value = e.message
            }

            _loading.value = false
        }
    }

    /* ---------------- HELPERS ---------------- */

    fun resetLoginState() {
        _loginSuccess.value = false
        _registerSuccess.value = false
    }

    fun logout() {
        viewModelScope.launch {
            repository.clearSession()
            tokenStore.clear()
        }
    }
}
