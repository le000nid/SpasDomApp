package com.example.spasdomuserapp.network

import com.example.spasdomuserapp.models.PlannedOrderPost
import com.example.spasdomuserapp.responses.PlannedResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface OrderApi {
    @POST("/planned-orders")
    suspend fun postPlannedOrder(@Body order: PlannedOrderPost): PlannedResponse
}