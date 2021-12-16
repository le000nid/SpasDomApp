package com.example.spasdomuserapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.spasdomuserapp.database.*
import com.example.spasdomuserapp.models.Order
import com.example.spasdomuserapp.models.OrderPost
import com.example.spasdomuserapp.network.OrderApi
import com.example.spasdomuserapp.network.SafeApiCall
import com.example.spasdomuserapp.responses.OrderListResponse
import com.example.spasdomuserapp.responses.OrderUpdate
import com.example.spasdomuserapp.responses.asCacheMarketModel
import com.example.spasdomuserapp.responses.asCachePlannedModel
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

    suspend fun insertPlannedOrderToCache(order: Order) {
        withContext(Dispatchers.IO) {
            cacheDao.insertPlannedOrder(order.asCachePlannerOrder())
        }
    }

    suspend fun getPlannedOrders() = safeApiCall { api.getPlannedOrders() }

    suspend fun insertAllPlannedOrdersToCache(orders: OrderListResponse) {
        withContext(Dispatchers.IO) {
            cacheDao.insertAllPlannedOrders(*orders.asCachePlannedModel())
        }
    }

    suspend fun updateCachePlannedOrder(order: Order) {
        withContext(Dispatchers.IO) {
            cacheDao.updatePlannedOrder(order.asCachePlannerOrder())
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

    suspend fun insertMarketOrderToCache(order: Order) {
        withContext(Dispatchers.IO) {
            cacheDao.insertMarketOrder(order.asCacheMarketOrder())
        }
    }

    // TODO( 1) delete hardcode in [cacheDatable] 2) uncomment below )
    suspend fun refreshMarketOrders() = safeApiCall {
        //val orders = api.getMarketOrders()
        //cacheDao.insertAllMarketOrders(*orders.asCacheMarketModel())
    }

    suspend fun updateCacheMarketOrder(order: Order) {
        withContext(Dispatchers.IO) {
            cacheDao.updateMarketOrder(order.asCacheMarketOrder())
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