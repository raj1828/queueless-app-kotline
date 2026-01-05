package com.example.queueless.ui.navigation

import com.example.queueless.data.remote.dto.OtpFlow

sealed class Routes(val route: String) {
    object Login: Routes("login")
    object Register: Routes("register")
    object Otp : Routes("otp/{flow}") {
        fun createRoute(flow: OtpFlow) = "otp/${flow.name}"
    }
    object Dashboard: Routes("dashboard")
}