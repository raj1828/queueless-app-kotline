package com.example.queueless.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.queueless.ui.auth.AuthState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("auth_pref")

class TokenDataStore(private val context: Context){
    suspend fun saveTokens(access: String, refresh: String ){
        context.dataStore.edit {
            it[TokenKeys.ACCESS_TOKEN] = access
            it[TokenKeys.REFRESH_TOKEN] = refresh
        }

        android.util.Log.d("TOKEN_STORE", "ACCESS: $access")
        android.util.Log.d("TOKEN_STORE", "REFRESH: $refresh")
    }

    val authState: Flow<AuthState> =
        context.dataStore.data.map { prefs ->
            val token = prefs[TokenKeys.ACCESS_TOKEN]
            if (token.isNullOrEmpty())
                AuthState.Unauthenticated
            else
                AuthState.Authenticated
        }

    suspend fun getAccessToken(): String? {
        return context.dataStore.data
            .first()[TokenKeys.ACCESS_TOKEN]
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}