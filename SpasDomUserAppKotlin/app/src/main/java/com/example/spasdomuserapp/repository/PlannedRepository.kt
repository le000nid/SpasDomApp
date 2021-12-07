package com.example.spasdomuserapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.spasdomuserapp.database.CacheDao
import com.example.spasdomuserapp.database.asCachePlannerOrder
import com.example.spasdomuserapp.database.asDomainPlannedOrder
import com.example.spasdomuserapp.models.PlannedOrder
import com.example.spasdomuserapp.models.PlannedOrderPost
import com.example.spasdomuserapp.network.OrderApi
import com.example.spasdomuserapp.network.SafeApiCall
import com.example.spasdomuserapp.responses.PlannedUpdate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PlannedRepository @Inject constructor(
    private val cacheDao: CacheDao,
    private val api: OrderApi
): SafeApiCall {

    val activePlannedOrders: LiveData<List<PlannedOrder>> =
        Transformations.map(cacheDao.getPlannedOrders(0)) {
            it.asDomainPlannedOrder()
        }

    val historyPlannedOrders: LiveData<List<PlannedOrder>> =
        Transformations.map(cacheDao.getPlannedOrders(1)) {
            it.asDomainPlannedOrder()
        }


    suspend fun refreshPlannedOrders() = safeApiCall {
        val orders = api.getPlannedOrders()
        //cacheDao.insertAllPlannedOrders(*orders.asCacheModel())
    }

    suspend fun updateCachePlannedOrder(order: PlannedOrder) {
        withContext(Dispatchers.IO) {
            cacheDao.updatePlannedOrder(order.asCachePlannerOrder())
        }
    }

    suspend fun putPlannedOrder(order: PlannedOrder) = safeApiCall {
        val userRate = PlannedUpdate("userRate", order.userRate.toString())
        val userReview = PlannedUpdate("userReview", order.userReview)
        val container = listOf(userRate, userReview)

        api.updatePlannedOrder(order.id, container)
    }

    suspend fun postPlannedOrder(order: PlannedOrderPost) = safeApiCall {
        api.postPlannedOrder(order)
    }
}