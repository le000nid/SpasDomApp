package com.example.spasdomuserapp.responses

import com.example.spasdomuserapp.database.CacheNewsItem
import com.example.spasdomuserapp.models.NewsItem

data class NewsResponse (
    val videos: List<NewsItem>
)

fun NewsResponse.asCacheNewsModel(): Array<CacheNewsItem> {
    return videos.map {
        CacheNewsItem (
            url = it.url,
            updated = it.updated,
            title = it.title,
            description = it.description,
            thumbnail = it.thumbnail
        )
    }.toTypedArray()
}