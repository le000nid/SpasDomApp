package com.example.spasdomworkerapp.responses

import com.example.spasdomworkerapp.database.DatabaseOrderItem
import com.example.spasdomworkerapp.models.Order
import com.example.spasdomworkerapp.models.PlannedOrder
import java.util.*

data class OrderListResponse(
    val order: List<PlannedOrder>
)

fun OrderListResponse.asCacheModel(): Array<DatabaseOrderItem> {
    return order.map {
        DatabaseOrderItem (
            date = it.date.substring(0, 10),
            address = it.title,
            time = it.date.substring(11,15),
            problem = it.title,
            active = false,
            finished  = false,
            id = it.id
        )
    }.toTypedArray()
}