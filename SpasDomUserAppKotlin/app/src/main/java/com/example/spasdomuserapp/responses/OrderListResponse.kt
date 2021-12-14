package com.example.spasdomuserapp.responses

import com.example.spasdomuserapp.database.CacheMarketOrder
import com.example.spasdomuserapp.database.CachePlannedOrder
import com.example.spasdomuserapp.models.Order

data class OrderListResponse (
    val order: List<Order>
)

fun OrderListResponse.asCachePlannedModel(): Array<CachePlannedOrder> {
    return order.map {
        CachePlannedOrder (
            id = it.id,
            title = it.title,
            date = it.date,
            time = it.time,
            userRate = it.userRate,
            userReview = it.userReview,
            status = it.status,
            workerImg = it.workerImg,
            workerName = it.workerName,
            workerRate = it.workerRate,
            workerInfo = it.workerInfo)
    }.toTypedArray()
}

fun OrderListResponse.asCacheMarketModel(): Array<CacheMarketOrder> {
    return order.map {
        CacheMarketOrder (
            id = it.id,
            title = it.title,
            date = it.date,
            time = it.time,
            userRate = it.userRate,
            userReview = it.userReview,
            status = it.status,
            workerImg = it.workerImg,
            workerName = it.workerName,
            workerRate = it.workerRate,
            workerInfo = it.workerInfo)
    }.toTypedArray()
}