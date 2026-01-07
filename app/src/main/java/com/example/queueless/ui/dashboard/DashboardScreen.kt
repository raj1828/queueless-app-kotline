package com.example.queueless.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.queueless.ui.auth.AuthViewModel.AuthViewModel
import com.example.queueless.ui.dashboard.components.DashboardBottomBar
import com.example.queueless.ui.homescreen.HomeScreen

@Composable
fun DashboardScreen(
    authViewModel: AuthViewModel
) {
    val dashboardNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            DashboardBottomBar(navController = dashboardNavController)
        }
    ) { paddingValues ->

        NavHost(
            navController = dashboardNavController,
            startDestination = "home",
//            modifier = Modifier.padding(paddingValues)
        ) {

            composable("home") {
                HomeScreen()
            }

            composable("queue") {
                QueueScreen()
            }

            composable("alerts") {
                AlertsScreen()
            }

            composable("search") {
                SearchScreen()
            }
        }
    }
}

@Composable
fun QueueScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Queue Screen")
    }
}

@Composable
fun AlertsScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Alerts Screen")
    }
}

@Composable
fun SearchScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Search Screen")
    }
}


