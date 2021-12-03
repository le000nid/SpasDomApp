package com.example.spasdomworkerapp.network

import com.example.spasdomworkerapp.database.DatabaseOrderItem
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkOrdersContainer(val orders: List<NetworkOrderItem>)

/**
 * News represent a newsItem that can be opened.
 */
@JsonClass(generateAdapter = true)
data class NetworkOrderItem(
    val date: String,
    val address: String,
    val time: String,
    val problem: String,
    val finished: Boolean,
    val id: Int)


fun NetworkOrdersContainer.asDatabaseModel(): Array<DatabaseOrderItem> {
    return orders.map {
        DatabaseOrderItem (
            date = it.date,
            address = it.address,
            time = it.time,
            problem = it.problem,
            finished  = it.finished,
            id = it.id)
    }.toTypedArray()
}

