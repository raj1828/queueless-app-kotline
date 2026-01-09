package com.example.queueless.data.remote.dto.dashboard

data class Location(
    val address: String,
    val city: String,
    val country: String,
    val lat: Double,
    val lng: Double,
    val state: String
)