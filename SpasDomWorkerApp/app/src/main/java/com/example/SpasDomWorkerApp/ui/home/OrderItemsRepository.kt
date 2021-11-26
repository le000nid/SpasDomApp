package com.example.spasdomworkerapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.spasdomworkerapp.database.CacheDatabase
import com.example.spasdomworkerapp.database.asDomainModel
import com.example.spasdomworkerapp.domain.Order
import com.example.spasdomworkerapp.network.NetworkOrderItem
import com.example.spasdomworkerapp.network.NetworkOrdersContainer
import com.example.spasdomworkerapp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class OrderItemsRepository (private val cache: CacheDatabase) {

    /**
     * A feed of newsItems that can be shown on the screen.
     */
    val newsItems: LiveData<List<Order>> =
        Transformations.map(cache.dao.getOrderItems()) {
            it.asDomainModel()
        }

    /**
     * Refresh the newsItems stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the newsItems for use, observe [newsItems]
     *
     * The Retrofit can't resolve the URL host when there is no connection.
     */
   suspend fun refreshOrderItems() {
        withContext(Dispatchers.IO) {
            try {
                //TODO(When null or empty is received it means that we need update actual data(clear all case))
//                val newsItems = Network.spasDom.getNewsItems()
                // Take first three items
//                val threeNews = NetworkOrdersContainer(newsItems.videos.take(3))

                val ordersInit: List<NetworkOrderItem> = listOf(
                    NetworkOrderItem("28 ноября", "Морской проспект 9", "13:00 - 14:00", "Бачок потик", false, false, 1),
                    NetworkOrderItem("29 ноября", "MLG 1337", "13:37 - 4:20", "О боже", false, false, 2),
                )

                val orders = NetworkOrdersContainer(ordersInit)
                cache.dao.insertAll(*orders.asDatabaseModel())
            } catch (e: Exception) {
                Timber.e("refreshOrders() error = %s", e.message)
            }
        }
    }
}