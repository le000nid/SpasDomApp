package com.example.spasdomuserapp.network

import com.example.spasdomuserapp.models.PlannedOrderPost
import com.example.spasdomuserapp.responses.*
import retrofit2.http.*

interface OrderApi {
    @POST("/planned-orders")
    suspend fun postPlannedOrder(@Body order: PlannedOrderPost): PlannedResponse

    @GET("/planned-orders")
    suspend fun getPlannedOrders(): PlannedListResponse

    @PUT("/planned-orders/{id}")
    suspend fun updatePlannedOrder(
        @Path("id") id: Int,
        @Body update: List<PlannedUpdate>) : Boolean

}