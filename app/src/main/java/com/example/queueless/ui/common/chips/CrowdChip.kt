package com.example.queueless.ui.common.chips

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.queueless.ui.homescreen.model.CrowdLevel

@Composable
fun CrowdChip(level: CrowdLevel) {
    val text = level.name.lowercase()
    val color = when (level) {
        CrowdLevel.LOW -> Color(0xFF1ABC9C)
        CrowdLevel.MEDIUM -> Color(0xFFF39C12)
        CrowdLevel.HIGH -> Color(0xFFE74C3C)
    }

    Surface(
        shape = RoundedCornerShape(50),
        color = color.copy(alpha = 0.15f)
    ) {
        Text(
            text = text,
            color = color,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall
        )
    }
}
