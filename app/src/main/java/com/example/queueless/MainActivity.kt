package com.example.queueless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.queueless.data.local.TokenDataStore
import com.example.queueless.data.local.db.AppDatabase
import com.example.queueless.data.remote.ApiClient
import com.example.queueless.data.repository.AuthRepository
import com.example.queueless.ui.auth.AuthViewModel.AuthViewModel
import com.example.queueless.ui.navigation.RootNavGraph
import com.example.queueless.ui.theme.QueueLessTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        /* ---------------- DEPENDENCIES ---------------- */

        val tokenStore = TokenDataStore(applicationContext)

        val database = AppDatabase.getInstance(applicationContext)
        val authDao = database.authDao()

        // 🔑 CREATE RETROFIT ONCE (WITH AUTH INTERCEPTOR)
        val retrofit = ApiClient.create(applicationContext) {
            // Logout callback (navigation handled in NavGraph)
        }

        // 🔑 PASS RETROFIT INTO REPOSITORY
        val authRepository = AuthRepository(
            authDao = authDao,
            retrofit = retrofit
        )

        val authViewModel = AuthViewModel(
            tokenStore = tokenStore,
            repository = authRepository
        )

        /* ---------------- UI ---------------- */

        setContent {
            QueueLessTheme {
                RootNavGraph(authViewModel = authViewModel, retrofit = retrofit)
            }
        }
    }
}
