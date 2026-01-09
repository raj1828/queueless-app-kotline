package com.example.queueless.ui.homescreen.model

data class ServiceItem(
    val name: String,
    val category: String,
    val address: String,
    val openTime: String,
    val closeTime: String,
    val isActive: Boolean
)


enum class CrowdLevel {
    LOW, MEDIUM, HIGH
}
