package com.example.spasdomuserapp.ui.services.planned

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.spasdomuserapp.database.CacheDatabase
import com.example.spasdomuserapp.database.asDomainActivePlannedOrder
import com.example.spasdomuserapp.domain.ActivePlannedOrder
import com.example.spasdomuserapp.network.NetworkActivePlannedOrdersContainer
import com.example.spasdomuserapp.network.asCacheActivePlannedOrderModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class PlannedRepository(private val cache: CacheDatabase) {

    val activePlannedOrders: LiveData<List<ActivePlannedOrder>> =
        Transformations.map(cache.dao.getActivePlannedOrders()) {
            it.asDomainActivePlannedOrder()
        }

    suspend fun refreshActivePlannedOrders() {
        withContext(Dispatchers.IO) {
            try {
                // TODO(val items = Network.spasDom.getActivePlannedOrders())
                val itemsInit: List<ActivePlannedOrder> = listOf(
                    ActivePlannedOrder( "Проверка счетчиков", "20.11.21  |  14:00 - 15:00"),
                )

                val items = NetworkActivePlannedOrdersContainer(itemsInit)
                cache.dao.insertAllActivePlannedOrders(*items.asCacheActivePlannedOrderModel())
            } catch (e: Exception) {
                Timber.e("refreshVideos() error = %s", e.message)
            }
        }
    }
}