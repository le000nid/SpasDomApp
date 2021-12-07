package com.example.spasdomuserapp.responses

import com.example.spasdomuserapp.database.CachePlannedOrder
import com.example.spasdomuserapp.models.PlannedOrder

data class PlannedListResponse (
    val order: List<PlannedOrder>
)

fun PlannedListResponse.asCacheModel(): Array<CachePlannedOrder> {
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