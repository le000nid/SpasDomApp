package com.example.spasdomuserapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.spasdomuserapp.database.CacheDao
import com.example.spasdomuserapp.database.asCachePlannerOrder
import com.example.spasdomuserapp.database.asDomainPlannedOrder
import com.example.spasdomuserapp.domain.PlannedOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class PlannedRepository(private val cacheDao: CacheDao) {

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
}