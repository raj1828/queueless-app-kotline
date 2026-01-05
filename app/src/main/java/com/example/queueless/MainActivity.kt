package com.example.queueless

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.queueless.data.local.TokenDataStore
import com.example.queueless.ui.auth.AuthViewModel.AuthViewModel
import com.example.queueless.ui.navigation.RootNavGraph
import com.example.queueless.ui.theme.QueueLessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tokenStore = TokenDataStore(this)
        val authViewModel = AuthViewModel(tokenStore)

        enableEdgeToEdge()


        setContent {
            QueueLessTheme {
                RootNavGraph(authViewModel)
            }
        }
    }
}