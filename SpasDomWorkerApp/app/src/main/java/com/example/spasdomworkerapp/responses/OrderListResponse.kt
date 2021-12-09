package com.example.spasdomworkerapp.responses

import com.example.spasdomworkerapp.database.DatabaseOrderItem
import com.example.spasdomworkerapp.models.Order
import com.example.spasdomworkerapp.models.PlannedOrder

data class OrderListResponse (
    val order: List<PlannedOrder>
)

fun OrderListResponse.asCacheModel(): Array<DatabaseOrderItem> {
    return order.map {
        DatabaseOrderItem (
            date = it.date,
            address = it.title,
            time = it.time,
            problem = it.title,
            active = false,
            finished  = false,
            id = it.id
        )
    }.toTypedArray()
}