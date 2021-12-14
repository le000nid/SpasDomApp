package com.example.spasdomuserapp.network

import com.example.spasdomuserapp.database.DataBaseAlert
import com.example.spasdomuserapp.database.DatabaseNewsItem

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
data class NetworkNewsContainer(val videos: List<NetworkNewsItem>)


/**
 * News represent a newsItem that can be opened.
 */
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



data class NetworkAlertsContainer(val alerts: List<NetworkAlerts>)

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