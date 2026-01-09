package com.example.queueless.ui.homescreen.mapper

import com.example.queueless.data.remote.dto.dashboard.Businesse
import com.example.queueless.ui.homescreen.model.ServiceItem

fun Businesse.toServiceItem(): ServiceItem {
    return ServiceItem(
        name = basicDetails.name ?: "Unnamed Business",
        category = category,
        address = "${location.address}, ${location.city}",
        openTime = operatingHours.open,
        closeTime = operatingHours.close,
        isActive = isActive
    )
}
