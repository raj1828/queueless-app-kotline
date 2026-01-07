package com.example.queueless.ui.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.queueless.ui.common.cards.ServiceCard
import com.example.queueless.ui.common.header.HeaderSection
import com.example.queueless.ui.homescreen.model.CrowdLevel
import com.example.queueless.ui.homescreen.model.ServiceItem

@Composable
fun HomeScreen() {

    val services = listOf(
        ServiceItem("City Health Clinic", "Downtown Medical Center", 0.8, CrowdLevel.LOW, 15),
        ServiceItem("Quick Car Service", "West Auto Plaza", 1.2, CrowdLevel.MEDIUM, 32),
        ServiceItem("Metro Bank Branch", "Financial District", 2.1, CrowdLevel.HIGH, 45),
        ServiceItem("Zen Spa & Salon", "Uptown Plaza", 1.5, CrowdLevel.LOW, 8),
        ServiceItem("City Hall Services", "Main Square", 2.8, CrowdLevel.MEDIUM, 28)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FB))
    ) {

        HeaderSection()

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(services) { service ->
                ServiceCard(
                    service = service,
                    onClick = {
                        // TODO: navigate to service details
                    }
                )
            }
        }
    }
}
