package com.example.spasdomuserapp.models

import android.os.Parcelable
import com.example.spasdomuserapp.database.CacheNewsItem
import com.example.spasdomuserapp.util.smartTruncate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsItem(
    val title: String,
    val description: String,
    val url: String,
    val date: String,
): Parcelable {
    val shortDescription: String
        get() = description.smartTruncate(200)
}

data class NetworkNewsItem(
    val title: String?,
    val description: String?,
    val url: String?,
    val date: String?,
)

fun List<NetworkNewsItem>.asCacheModel(): Array<CacheNewsItem> {
    return map {
        CacheNewsItem(
            title = it.title ?: "",
            description = it.description ?: "",
            url = it.url ?: "",
            date = it.date ?: "",
        )
    }.toTypedArray()
}