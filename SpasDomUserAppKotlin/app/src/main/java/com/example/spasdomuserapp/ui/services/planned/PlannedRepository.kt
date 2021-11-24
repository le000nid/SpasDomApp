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
                    PlannedOrder(1,"Проверка счетчиков","24.01.21","14:00-15:00",0,"",false,"","Петр Васильев", 4, "No info")
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
                    PlannedOrder(2,"Проверка воды","25.01.21","17:00-18:00",0,"",true,"","Александр Васильев", 2, "No info"),
                    PlannedOrder(3,"Проверка крана","27.01.21","12:00-13:00",4,"Все прекрасно",true,"","Петр Васильев", 4, "No info")
                )

                val items = NetworkPlannedOrdersContainer(itemsInit)
                cache.dao.insertAllPlannedOrders(*items.asCachePlannedOrderModel())
            } catch (e: Exception) {
                Timber.e("refreshVideos() error = %s", e.message)
            }
        }
    }
}