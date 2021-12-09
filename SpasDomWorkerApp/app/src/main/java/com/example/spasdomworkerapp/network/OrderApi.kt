package com.example.spasdomworkerapp.network

import com.example.spasdomworkerapp.models.Order
import com.example.spasdomworkerapp.responses.OrderListResponse
import com.example.spasdomworkerapp.responses.OrderUpdate
import retrofit2.http.*

interface OrderApi {

    @GET("/planned-orders")
    suspend fun getPlannedOrders(): OrderListResponse

    @PUT("/planned-orders/{id}")
    suspend fun updatePlannedOrder(
        @Path("id") id: Int,
        @Body update: List<OrderUpdate>) : Boolean

}