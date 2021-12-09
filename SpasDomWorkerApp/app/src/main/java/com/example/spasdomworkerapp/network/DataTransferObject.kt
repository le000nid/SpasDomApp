package com.example.spasdomworkerapp.network

import com.example.spasdomworkerapp.database.DatabaseOrderItem
import com.squareup.moshi.JsonClass

data class NetworkOrdersContainer(val orders: List<NetworkOrderItem>)

data class NetworkOrder(val order: NetworkOrderItem)

data class NetworkOrderItem(
    val date: String,
    val address: String,
    val time: String,
    val problem: String,
    val active: Boolean,
    val finished: Boolean,
    val id: Int)


fun NetworkOrdersContainer.asDatabaseModel(): Array<DatabaseOrderItem> {
    return orders.map {
        DatabaseOrderItem (
            date = it.date,
            address = it.address,
            time = it.time,
            problem = it.problem,
            active = it.active,
            finished  = it.finished,
            id = it.id)
    }.toTypedArray()
}

fun NetworkOrder.asDatabaseModel(): DatabaseOrderItem {
    return DatabaseOrderItem (
        date = order.date,
        address = order.address,
        time = order.time,
        problem = order.problem,
        active = order.active,
        finished  = order.finished,
        id = order.id)
}
