package com.example.spasdomworkerapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.spasdomworkerapp.database.*
import com.example.spasdomworkerapp.models.Order
import com.example.spasdomworkerapp.network.NetworkOrderItem
import com.example.spasdomworkerapp.network.NetworkOrdersContainer
import com.example.spasdomworkerapp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


class OrderItemsRepository (private val cache: CacheDatabase) {

    /**
     * A feed of newsItems that can be shown on the screen.
     */

    fun getOrderItems(getData: String) : LiveData<List<Order>>{
        val orderItems: LiveData<List<Order>> = Transformations.map(cache.dao.getOrderItems(getData)) {
            it.asDomainModel()
        }
        return orderItems
    }

    /**
     * Refresh the newsItems stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the orderItems for use, observe [orderItems]
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
                    NetworkOrderItem("27-11-2021", "Улица улная", "19:00 - 20:00", "Нада", true, false, 5),
                    NetworkOrderItem("28-11-2021", "Улица уная", "11:00 - 12:00", "Нада", true,false, 1),
                    NetworkOrderItem("28-11-2021", "Улица уличная", "13:00 - 14:00", "Нада", false,false, 2),
                    NetworkOrderItem("28-11-2021", "Улица улиная", "15:00 - 16:00", "Нада", false,false, 3),
                    NetworkOrderItem("28-11-2021", "Морской проспект 9", "17:00 - 18:00", "Бачок потик", true,false, 4),
                    NetworkOrderItem("29-11-2021", "Не морской не проспект", "3:30 - 4:20", "О боже", false,false, 6),
                    NetworkOrderItem("05-12-2021", "5 декабря :)", "3:30 - 4:20", "Работает", false,false, 7),
                    NetworkOrderItem("06-12-2021", "6 декабря :)", "3:30 - 4:20", "Работает", false,false, 8),
                )

                val orders = NetworkOrdersContainer(ordersInit)
                cache.dao.insertAll(*orders.asDatabaseModel())
            } catch (e: Exception) {
                Timber.e("refreshOrders() error = %s", e.message)
            }
        }
    }
}