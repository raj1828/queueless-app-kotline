package com.example.queueless.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.queueless.ui.auth.AuthState
import com.example.queueless.ui.auth.AuthViewModel.AuthViewModel
import com.example.queueless.ui.auth.login.LoginScreen
import com.example.queueless.ui.auth.otp.OtpScreen
import com.example.queueless.ui.auth.register.RegisterScreen
import com.example.queueless.ui.dashboard.DashboardScreen

@Composable
fun RootNavGraph(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    val authState by authViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        when(authState){
            AuthState.Authenticated -> {
                navController.navigate(Routes.Dashboard.route){
                    popUpTo(0)
                }
            }
            AuthState.Unauthenticated -> {
                navController.navigate(Routes.Login.route){
                    popUpTo(0)
                }
            }
            else -> Unit
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route){
            LoginScreen(navController)
        }
        composable(Routes.Otp.route){
            OtpScreen(navController, authViewModel = authViewModel)
        }
        composable(Routes.Register.route){
            RegisterScreen(navController)
        }
        composable(Routes.Dashboard.route){
            DashboardScreen(authViewModel)
        }
    }
}