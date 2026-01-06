package com.example.queueless.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
) {

    object Home: BottomNavItem(
        route= "home",
        title= "Home",
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home
    )

    object Queue: BottomNavItem(
        route = "queue",
        title = "Queue",
        icon = Icons.Outlined.Build,
        selectedIcon = Icons.Filled.Build
    )

    object Alerts : BottomNavItem(
        route = "alerts",
        title = "Alerts",
        icon = Icons.Outlined.Notifications,
        selectedIcon = Icons.Filled.Notifications
    )

    object Search : BottomNavItem(
        route = "search",
        title = "Search",
        icon = Icons.Outlined.Search,
        selectedIcon = Icons.Filled.Search
    )
}
