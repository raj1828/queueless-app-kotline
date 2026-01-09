package com.example.queueless.ui.common.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.queueless.ui.common.chips.CrowdChip
import com.example.queueless.ui.common.chips.InfoChip
import com.example.queueless.ui.common.chips.TimeBadge
import com.example.queueless.ui.homescreen.model.CrowdLevel
import com.example.queueless.ui.homescreen.model.ServiceItem

@Composable
fun ServiceCard(
    service: ServiceItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = service.name,
                    style = MaterialTheme.typography.titleMedium
                )

                TimeBadge(15)
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = service.address,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                InfoChip(service.category)
                CrowdChip(CrowdLevel.LOW)
            }
        }
    }
}

