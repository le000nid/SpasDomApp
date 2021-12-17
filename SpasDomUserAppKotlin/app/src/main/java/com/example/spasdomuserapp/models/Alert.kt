package com.example.spasdomuserapp.models

import com.example.spasdomuserapp.database.CacheAlert

data class Alert(
    val id: Int,
    val date: String,
    val title: String,
    val body: String
)

data class NetworkAlert(
    val id: Int,
    val date: String?,
    val title: String?,
    val body: String?
)

fun List<NetworkAlert>.asCacheModel(): Array<CacheAlert> {
    return map {
        CacheAlert(
            id = it.id,
            date = it.date ?: "",
            title = it.title ?: "",
            body = it.body ?: ""
        )
    }.toTypedArray()
}