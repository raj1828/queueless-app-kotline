package com.example.queueless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.queueless.data.local.TokenDataStore
import com.example.queueless.data.local.db.AppDatabase
import com.example.queueless.data.repository.AuthRepository
import com.example.queueless.ui.auth.AuthViewModel.AuthViewModel
import com.example.queueless.ui.navigation.RootNavGraph
import com.example.queueless.ui.theme.QueueLessTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        /* ---------------- DEPENDENCIES ---------------- */

        val tokenStore = TokenDataStore(this)

        val database = AppDatabase.getInstance(this)
        val authDao = database.authDao()

        val authRepository = AuthRepository(authDao)

        val authViewModel = AuthViewModel(
            tokenStore = tokenStore,
            repository = authRepository
        )

        /* ---------------- UI ---------------- */

        setContent {
            QueueLessTheme {
                RootNavGraph(authViewModel)
            }
        }
    }
}
