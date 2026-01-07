package com.example.queueless.ui.common.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.queueless.ui.common.inputs.SearchBar

@Composable
fun HeaderSection() {

    var searchQuery by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF2F66F3),
                shape = RoundedCornerShape(
                    bottomStart = 28.dp,
                    bottomEnd = 28.dp
                )
            )
            .statusBarsPadding()   // ✅ THIS IS THE FIX
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {

        Text(
            text = "Discover Services",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(12.dp))

        SearchBar(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = "Search for services nearby..."
        )
        Spacer(modifier = Modifier.height(12.dp))

    }
}
