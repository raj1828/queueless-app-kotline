package com.example.queueless.ui.homescreen.model

data class ServiceItem(
    val name: String,
    val location: String,
    val distanceKm: Double,
    val crowdLevel: CrowdLevel,
    val waitTimeMin: Int
)

enum class CrowdLevel {
    LOW, MEDIUM, HIGH
}
