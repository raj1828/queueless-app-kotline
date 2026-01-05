package com.example.queueless.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.queueless.data.remote.dto.OtpFlow
import com.example.queueless.ui.auth.AuthViewModel.AuthViewModel
import com.example.queueless.ui.auth.login.LoginScreen
import com.example.queueless.ui.auth.otp.OtpScreen
import com.example.queueless.ui.auth.register.RegisterScreen
import com.example.queueless.ui.dashboard.DashboardScreen

@Composable
fun RootNavGraph(authViewModel: AuthViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

        composable(Routes.Login.route) {
            LoginScreen(navController, authViewModel)
        }

        composable(Routes.Register.route) {
            RegisterScreen(navController, authViewModel)
        }

        composable(
            route = Routes.Otp.route,
            arguments = listOf(
                navArgument("flow") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val flow = OtpFlow.valueOf(
                backStackEntry.arguments?.getString("flow")!!
            )

            OtpScreen(
                navController = navController,
                authViewModel = authViewModel,
                flow = flow
            )
        }

        composable(Routes.Dashboard.route) {
            DashboardScreen(authViewModel)
        }
    }
}
