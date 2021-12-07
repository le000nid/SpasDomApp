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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class PlannedRepository @Inject constructor(
    private val cacheDao: CacheDao,
    private val api: OrderApi
): SafeApiCall {

    val activePlannedOrders: LiveData<List<PlannedOrder>> =
        Transformations.map(cacheDao.getActivePlannedOrders()) {
            it.asDomainPlannedOrder()
        }

    val historyPlannedOrders: LiveData<List<PlannedOrder>> =
        Transformations.map(cacheDao.getHistoryPlannedOrders()) {
            it.asDomainPlannedOrder()
        }

    suspend fun refreshActivePlannedOrders() {
        withContext(Dispatchers.IO) {
            try {

            } catch (e: Exception) {
                Timber.e("refreshVideos() error = %s", e.message)
            }
        }
    }

    suspend fun refreshHistoryPlannedOrders() {
        withContext(Dispatchers.IO) {
            try {

            } catch (e: Exception) {
                Timber.e("refreshVideos() error = %s", e.message)
            }
        }
    }

    suspend fun updatePlannedOrder(newPlannedOrder: PlannedOrder) {
        withContext(Dispatchers.IO) {
            cacheDao.updatePlannedOrder(newPlannedOrder.asCachePlannerOrder())
        }
    }

    suspend fun postPlannedOrder(order: PlannedOrderPost) = safeApiCall {
        api.postPlannedOrder(order)
    }
}