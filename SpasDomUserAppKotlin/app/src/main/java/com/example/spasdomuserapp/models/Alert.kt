package com.example.spasdomuserapp.models

import com.example.spasdomuserapp.database.CacheAlert

data class Alert(
    val id: Int,
    val data: String,
    val title: String,
    val description: String
)

data class NetworkAlert(
    val id: Int,
    val data: String?,
    val title: String?,
    val description: String?
)

fun List<NetworkAlert>.asCacheAlertsModel(): Array<CacheAlert> {
    return map {
        CacheAlert(
            id = it.id,
            data = it.data ?: "",
            title = it.title ?: "",
            description = it.description ?: ""
        )
    }.toTypedArray()
}