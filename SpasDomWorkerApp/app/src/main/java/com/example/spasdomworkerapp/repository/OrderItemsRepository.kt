package com.example.spasdomworkerapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.spasdomworkerapp.database.*
import com.example.spasdomworkerapp.models.Order
import com.example.spasdomworkerapp.network.NetworkOrder
import com.example.spasdomworkerapp.network.NetworkOrderItem
import com.example.spasdomworkerapp.network.NetworkOrdersContainer
import com.example.spasdomworkerapp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class OrderItemsRepository @Inject constructor(
    private val cacheDao: CacheDao,
) {

    /**
     * A feed of newsItems that can be shown on the screen.
     */

    fun getOrderItems(getData: String) : LiveData<List<Order>>{
        val orderItems: LiveData<List<Order>> = Transformations.map(cacheDao.getOrderItems(getData)) {
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

    suspend fun saveOrderItem(itemOrder: NetworkOrderItem){
        cacheDao.insertAllOrders(NetworkOrder(itemOrder).asDatabaseModel())
    }

    suspend fun refreshOrderItems() {
        withContext(Dispatchers.IO) {
            try {

            } catch (e: Exception) {
                Timber.e("refreshVideos() error = %s", e.message)
            }
        }
    }
}