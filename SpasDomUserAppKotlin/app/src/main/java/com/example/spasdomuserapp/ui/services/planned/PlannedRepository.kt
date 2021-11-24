package com.example.spasdomuserapp.ui.services.planned

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.spasdomuserapp.database.CacheDatabase
import com.example.spasdomuserapp.database.asDomainPlannedOrder
import com.example.spasdomuserapp.domain.PlannedOrder
import com.example.spasdomuserapp.network.NetworkPlannedOrdersContainer
import com.example.spasdomuserapp.network.asCachePlannedOrderModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class PlannedRepository(private val cache: CacheDatabase) {

    val activePlannedOrders: LiveData<List<PlannedOrder>> =
        Transformations.map(cache.dao.getActivePlannedOrders()) {
            it.asDomainPlannedOrder()
        }

    val historyPlannedOrders: LiveData<List<PlannedOrder>> =
        Transformations.map(cache.dao.getHistoryPlannedOrders()) {
            it.asDomainPlannedOrder()
        }

    suspend fun refreshActivePlannedOrders() {
        withContext(Dispatchers.IO) {
            try {
                // TODO(val items = Network.spasDom.getPlannedOrders())
                val itemsInit: List<PlannedOrder> = listOf(
                    PlannedOrder( "Проверка счетчиков", "20.11.21  |  14:00 - 15:00",1,"null", false),
                )

                val items = NetworkPlannedOrdersContainer(itemsInit)
                cache.dao.insertAllPlannedOrders(*items.asCachePlannedOrderModel())
            } catch (e: Exception) {
                Timber.e("refreshVideos() error = %s", e.message)
            }
        }
    }

    suspend fun refreshHistoryPlannedOrders() {
        withContext(Dispatchers.IO) {
            try {
                // TODO(val items = Network.spasDom.getPlannedOrders())
                val itemsInit: List<PlannedOrder> = listOf(
                    PlannedOrder( "Проверка счетчиков", "20.12.21  |  14:00 - 15:00",null,null, true),
                    PlannedOrder( "Проверка счетчиков", "20.13.21  |  14:00 - 15:00",4,null, true),
                )

                val items = NetworkPlannedOrdersContainer(itemsInit)
                cache.dao.insertAllPlannedOrders(*items.asCachePlannedOrderModel())
            } catch (e: Exception) {
                Timber.e("refreshVideos() error = %s", e.message)
            }
        }
    }
}