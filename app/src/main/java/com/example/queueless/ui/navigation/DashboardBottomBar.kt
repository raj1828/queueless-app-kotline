package com.example.queueless.ui.dashboard.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.queueless.ui.navigation.BottomNavItem

@Composable
fun DashboardBottomBar(
    navController: NavController
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Alerts,
        BottomNavItem.Queue
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .height(72.dp)
                .padding(horizontal = 24.dp)
                .shadow(16.dp, RoundedCornerShape(36.dp))
                .background(Color.White, RoundedCornerShape(36.dp))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            items.forEach { item ->
                val selected = currentRoute == item.route
                val offsetY by animateDpAsState(
                    targetValue = if (selected) (-18).dp else 0.dp,
                    label = ""
                )

                Column(
                    modifier = Modifier
                        .offset(y = offsetY)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(
                                if (selected) Color(0xFF6A1B9A) else Color.Transparent,
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo("home") { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (selected) item.selectedIcon else item.icon,
                                contentDescription = item.title,   // ✅ FIXED
                                tint = if (selected) Color.White else Color.Gray
                            )
                        }
                    }

                    if (selected) {
                        Text(
                            text = item.title,               // ✅ FIXED
                            color = Color(0xFF6A1B9A),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}
