package com.example.queueless.ui.navigation

sealed class Routes(val route: String) {
    object Login: Routes("login")
    object Register: Routes("register")
    object Otp: Routes("otp")
    object Dashboard: Routes("dashboard")
}