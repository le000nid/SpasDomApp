package com.example.spasdomuserapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.spasdomuserapp.database.CacheDao
import com.example.spasdomuserapp.database.asDomainMarketOrder
import com.example.spasdomuserapp.database.asDomainPlannedOrder
import com.example.spasdomuserapp.models.*
import com.example.spasdomuserapp.network.OrderApi
import com.example.spasdomuserapp.network.SafeApiCall
import com.example.spasdomuserapp.responses.OrderUpdate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrdersRepository @Inject constructor(
    private val cacheDao: CacheDao,
    private val api: OrderApi
): SafeApiCall {

    val activePlannedOrders: LiveData<List<Order>> =
        Transformations.map(cacheDao.getPlannedOrders(0)) {
            it.asDomainPlannedOrder()
        }

    val historyPlannedOrders: LiveData<List<Order>> =
        Transformations.map(cacheDao.getPlannedOrders(1)) {
            it.asDomainPlannedOrder()
        }

    suspend fun getPlannedOrders() = safeApiCall { api.getPlannedOrders() }

    suspend fun insertAllPlannedOrdersToCache(orders: List<NetworkOrder>) {
        withContext(Dispatchers.IO) {
            cacheDao.insertAllPlannedOrders(*orders.asCachePlannedModel())
        }
    }

    suspend fun putPlannedOrder(order: Order) = safeApiCall {
        val userRate = OrderUpdate("userRate", order.userRate.toString())
        val userReview = OrderUpdate("userReview", order.userReview)
        val container = listOf(userRate, userReview)

        api.updatePlannedOrder(order.id, container)
    }

    suspend fun postPlannedOrder(order: OrderPost) = safeApiCall {
        api.postPlannedOrder(order)
    }



    val activeMarketOrders: LiveData<List<Order>> =
        Transformations.map(cacheDao.getMarketOrders(0)) {
            it.asDomainMarketOrder()
        }

    val historyMarketOrders: LiveData<List<Order>> =
        Transformations.map(cacheDao.getMarketOrders(1)) {
            it.asDomainMarketOrder()
        }

    suspend fun getMarketOrders() = safeApiCall { api.getMarketOrders() }

    suspend fun insertAllMarketOrdersToCache(orders: List<NetworkOrder>) {
        withContext(Dispatchers.IO) {
            cacheDao.insertAllMarketOrders(*orders.asCacheMarketModel())
        }
    }

    suspend fun putMarketOrder(order: Order) = safeApiCall {
        val userRate = OrderUpdate("userRate", order.userRate.toString())
        val userReview = OrderUpdate("userReview", order.userReview)
        val container = listOf(userRate, userReview)

        api.updateMarketOrder(order.id, container)
    }

    suspend fun postMarketOrder(order: OrderPost) = safeApiCall {
        api.postMarketOrder(order)
    }
}