package com.example.queueless.ui.auth.AuthViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.queueless.data.local.TokenDataStore
import com.example.queueless.ui.auth.AuthState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel (
    private  val tokenStore: TokenDataStore
): ViewModel() {
    val authState: StateFlow<AuthState> =
        tokenStore.authState.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            AuthState.Loading
        )

    fun loginSuccess(access: String, refresh: String) {
        viewModelScope.launch {
            tokenStore.saveTokens(access, refresh)
        }
    }

    fun logout() {
        viewModelScope.launch {
            tokenStore.clear()
        }
    }
}