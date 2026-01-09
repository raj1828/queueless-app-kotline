package com.example.queueless.data.remote.dto.dashboard

data class Businesse(
    val __v: Int,
    val _id: String,
    val basicDetails: BasicDetails,
    val category: String,
    val createdAt: String,
    val isActive: Boolean,
    val location: Location,
    val operatingHours: OperatingHours,
    val updatedAt: String
)