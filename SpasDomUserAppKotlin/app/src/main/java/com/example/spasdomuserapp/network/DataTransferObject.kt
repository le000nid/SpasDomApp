package com.example.spasdomuserapp.network

import com.example.spasdomuserapp.database.CachePlannedOrder
import com.example.spasdomuserapp.database.DataBaseAlert
import com.example.spasdomuserapp.database.DatabaseNewsItem
import com.example.spasdomuserapp.domain.PlannedOrder
import com.squareup.moshi.JsonClass

/**
 * DataTransferObjects go in this file. These are responsible for parsing responses from the server
 * or formatting objects to send to the server. You should convert these to domain objects before
 * using them.
 */

/**
 * NewsHolder holds a list of Videos.
 * This is to parse first level of our network result which looks like
 * {
 *   "news": []
 * }
 */
@JsonClass(generateAdapter = true)
data class NetworkNewsContainer(val videos: List<NetworkNewsItem>)

data class LoginObject(
    val businessAccount: String,
    val password: String,
    val firebaseToken: String
)

/**
 * News represent a newsItem that can be opened.
 */
@JsonClass(generateAdapter = true)
data class NetworkNewsItem(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?)


fun NetworkNewsContainer.asDatabaseModel(): Array<DatabaseNewsItem> {
    return videos.map {
        DatabaseNewsItem (
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }.toTypedArray()
}



@JsonClass(generateAdapter = true)
data class NetworkAlertsContainer(val alerts: List<NetworkAlerts>)

@JsonClass(generateAdapter = true)
data class NetworkAlerts(
    val data: String,
    val title: String,
    val description: String)

fun NetworkAlertsContainer.asDatabaseAlertModel(): Array<DataBaseAlert> {
    return alerts.map {
        DataBaseAlert (
            data = it.data,
            title = it.title,
            description = it.description)
    }.toTypedArray()
}



@JsonClass(generateAdapter = true)
data class NetworkPlannedOrdersContainer(val orders: List<PlannedOrder>)

@JsonClass(generateAdapter = true)
data class NetworkPlannedOrders(
    val id: Int,
    val title: String,
    val date: String,
    val time: String,
    val userRate: Int,
    val userReview: String,
    val isFinished: Boolean,
    val workerImg: String,
    val workerName: String,
    val workerRate: Int,
    val workerInfo: String)

fun NetworkPlannedOrdersContainer.asCachePlannedOrderModel(): Array<CachePlannedOrder> {
    return orders.map {
        CachePlannedOrder (
            id = it.id,
            title = it.title,
            date = it.date,
            time = it.time,
            userRate = it.userRate,
            userReview = it.userReview,
            isFinished = it.isFinished,
            workerImg = it.workerImg,
            workerName = it.workerName,
            workerRate = it.workerRate,
            workerInfo = it.workerInfo)
    }.toTypedArray()
}