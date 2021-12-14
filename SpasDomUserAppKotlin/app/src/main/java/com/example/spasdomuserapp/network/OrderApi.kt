package com.example.spasdomuserapp.network

import com.example.spasdomuserapp.models.OrderPost
import com.example.spasdomuserapp.responses.*
import retrofit2.http.*

interface OrderApi {
    @POST("/planned-orders")
    suspend fun postPlannedOrder(@Body order: OrderPost): OrderResponse

    @GET("/planned-orders")
    suspend fun getPlannedOrders(): OrderListResponse

    @PUT("/planned-orders/{id}")
    suspend fun updatePlannedOrder(
        @Path("id") id: Int,
        @Body update: List<OrderUpdate>) : Boolean

    @GET("/planned-categories")
    suspend fun getPlannedCategories(): CategoriesResponse


    @POST("/marked-orders")
    suspend fun postMarketOrder(@Body order: OrderPost): OrderResponse

    @GET("/marked-orders")
    suspend fun getMarketOrders(): OrderListResponse

    @PUT("/marked-orders/{id}")
    suspend fun updateMarketOrder(
        @Path("id") id: Int,
        @Body update: List<OrderUpdate>) : Boolean

    @GET("/market-categories")
    suspend fun getMarketCategories(): SectionCategoriesResponse

    @GET("/market-workers")
    suspend fun getMarketPreviewWorkers(@Query("filter") type: String): WorkersPreviewResponse
}