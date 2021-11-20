package com.example.spasdomuserapp.network

import com.example.spasdomuserapp.database.DatabaseNewsItem
import com.example.spasdomuserapp.domain.NewsItem
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

/**
 * Convert Network results to database objects
 */
fun NetworkNewsContainer.asDomainModel(): List<NewsItem> {
    return videos.map {
        NewsItem(
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail)
    }
}

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

