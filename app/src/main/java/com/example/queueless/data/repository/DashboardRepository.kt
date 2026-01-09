package com.example.queueless.data.repository

import com.example.queueless.data.remote.dashboard.DashboardApi
import com.example.queueless.data.remote.dto.dashboard.ListOfBusinessesResponse
import retrofit2.Retrofit

class DashboardRepository(
    retrofit: Retrofit
) {
    private val api = retrofit.create(DashboardApi::class.java)

    suspend fun getAllBusinesses(): ListOfBusinessesResponse {
        return api.getAllBusiness()
    }
}
