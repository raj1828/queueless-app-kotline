package com.example.queueless.data.remote.dashboard

import com.example.queueless.data.remote.dto.dashboard.ListOfBusinessesResponse
import retrofit2.http.GET

interface DashboardApi {
    @GET("api/business")
    suspend fun getAllBusiness(): ListOfBusinessesResponse
}