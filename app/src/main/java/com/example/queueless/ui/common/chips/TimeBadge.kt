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

@Composable
fun TimeBadge(minutes: Int) {
    val color = when {
        minutes <= 15 -> Color(0xFF1ABC9C)
        minutes <= 30 -> Color(0xFFF39C12)
        else -> Color(0xFFE74C3C)
    }

    Surface(
        color = color,
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = "$minutes min",
            color = Color.White,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall
        )
    }
}
