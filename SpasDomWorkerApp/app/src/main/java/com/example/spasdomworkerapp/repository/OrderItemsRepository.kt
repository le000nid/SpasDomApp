package com.example.spasdomworkerapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.spasdomworkerapp.database.*
import com.example.spasdomworkerapp.models.Order
import com.example.spasdomworkerapp.network.*
import com.example.spasdomworkerapp.responses.asCacheModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class OrderItemsRepository @Inject constructor(
    private val cacheDao: CacheDao,
    private val api: OrderApi
): SafeApiCall {

    fun getOrderItems(getData: String) : LiveData<List<Order>> {
        val orderItems: LiveData<List<Order>> =
            Transformations.map(cacheDao.getOrderItems(getData)) {
                it.asDomainOrder()
            }
        return orderItems
    }

    suspend fun saveOrderItem(itemOrder: NetworkOrderItem){
        cacheDao.insertAllOrders(NetworkOrder(itemOrder).asDatabaseModel())
    }

//    suspend fun refreshOrderItems() {
//        val orders = api.getPlannedOrders()
//        //cacheDao.insertAllOrders(*orders.asCacheModel())
//    }
}