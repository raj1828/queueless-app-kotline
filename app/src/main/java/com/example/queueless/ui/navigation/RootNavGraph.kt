package com.example.queueless.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.queueless.data.repository.DashboardRepository
import com.example.queueless.ui.auth.AuthViewModel.AuthViewModel
import com.example.queueless.ui.auth.login.LoginScreen
import com.example.queueless.ui.auth.otp.OtpScreen
import com.example.queueless.ui.auth.register.RegisterScreen
import com.example.queueless.ui.dashboard.DashboardScreen
import com.example.queueless.ui.dashboard.dashboard_viewmodel.DashboardViewModel
import com.example.queueless.data.remote.dto.OtpFlow
import retrofit2.Retrofit

@Composable
fun RootNavGraph(
    authViewModel: AuthViewModel,
    retrofit: Retrofit          // 🔑 SAME retrofit created in MainActivity
) {

    val navController = rememberNavController()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    /* ---------------- DASHBOARD DEPENDENCIES ---------------- */

    val dashboardRepository = remember {
        DashboardRepository(retrofit)
    }

    val dashboardViewModel = remember {
        DashboardViewModel(dashboardRepository)
    }

    /* ---------------- AUTH STATE NAVIGATION ---------------- */

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            navController.navigate(Routes.Dashboard.route) {
                popUpTo(0)
            }
        } else {
            navController.navigate(Routes.Login.route) {
                popUpTo(0)
            }
        }
    }

    /* ---------------- NAV GRAPH ---------------- */

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

        composable(Routes.Login.route) {
            LoginScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(Routes.Register.route) {
            RegisterScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(
            route = Routes.Otp.route,
            arguments = listOf(
                navArgument("flow") { type = NavType.StringType }
            )
        ) {
            OtpScreen(
                navController = navController,
                authViewModel = authViewModel,
                flow = OtpFlow.valueOf(
                    it.arguments?.getString("flow")!!
                )
            )
        }

        composable(Routes.Dashboard.route) {
            DashboardScreen(
                authViewModel = authViewModel,
                dashboardViewModel = dashboardViewModel
            )
        }
    }
}
