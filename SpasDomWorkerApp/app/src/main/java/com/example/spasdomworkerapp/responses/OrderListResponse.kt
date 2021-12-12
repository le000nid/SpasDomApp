package com.example.spasdomworkerapp.responses

import com.example.spasdomworkerapp.database.DatabaseOrderItem
import com.example.spasdomworkerapp.models.Order
import com.example.spasdomworkerapp.models.PlannedOrder
import java.util.*

data class OrderListResponse(
    val order: List<Order>
)

fun OrderListResponse.asCacheModel(): Array<DatabaseOrderItem> {
    return order.map {
        DatabaseOrderItem (
            date = it.date,
            address = it.address,
            time = it.time,
            problem = it.problem,
            status = it.status,
            id = it.id
        )
    }.toTypedArray()
}