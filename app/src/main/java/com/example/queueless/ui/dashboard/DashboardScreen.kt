package com.example.queueless.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.queueless.ui.auth.AuthViewModel.AuthViewModel

@Composable
fun DashboardScreen(authViewModel: AuthViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome to QueueLess 🎉")

        Spacer(Modifier.height(20.dp))

        Button(onClick = { authViewModel.logout() }) {
            Text("Logout")
        }
    }
}
