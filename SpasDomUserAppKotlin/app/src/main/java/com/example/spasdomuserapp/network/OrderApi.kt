package com.example.spasdomuserapp.network

import com.example.spasdomuserapp.models.*
import com.example.spasdomuserapp.responses.*
import retrofit2.http.*

interface OrderApi {

    // ---------- Planned orders ----------

    @POST("/planned-orders")
    suspend fun postPlannedOrder(@Body plannedOrder: PlannedOrderPost): PlannedOrderResponse

    @GET("/planned-orders")
    suspend fun getPlannedOrders(): List<NetworkOrder>

    @PUT("/planned-orders/{id}")
    suspend fun updatePlannedOrder(
        @Path("id") id: Int,
        @Body update: List<OrderUpdate>) : Boolean

    @GET("/planned-categories")
    suspend fun getPlannedCategories(): List<CategoriesList>

    @GET("/calendar")
    suspend fun getWorkerCalendar(@Query("subcategory") subcategoryId: Int): List<WorkerMonth>


    // ---------- Market orders ----------

    @POST("/marked-orders")
    suspend fun postMarketOrder(@Body plannedOrder: MarketOrderPost): MarketOrderResponse

    @GET("/marked-orders")
    suspend fun getMarketOrders(): List<NetworkOrder>

    @PUT("/marked-orders/{id}")
    suspend fun updateMarketOrder(
        @Path("id") id: Int,
        @Body update: List<OrderUpdate>) : Boolean

    @GET("/market-categories")
    suspend fun getMarketCategories(): List<SectionCategories>

    @GET("/market-workers")
    suspend fun getMarketPreviewWorkers(@Query("???") categoryId: Int): List<WorkerPreview>

    @GET("/market-workers")
    suspend fun getMarketWorker(@Query("???") workerId: Int): Worker

    @GET("/calendar")
    suspend fun getWorkerCalendarById(@Query("worker") workerId: Int): List<WorkerMonth>
}