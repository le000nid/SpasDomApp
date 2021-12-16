package com.example.spasdomuserapp.responses

import com.example.spasdomuserapp.database.CacheAlert
import com.example.spasdomuserapp.models.Alert

data class AlertsResponse (
    val alerts: List<Alert>
)

fun AlertsResponse.asCacheAlertModel(): Array<CacheAlert> {
    return alerts.map {
        CacheAlert (
            data = it.data,
            title = it.title,
            description = it.description
        )
    }.toTypedArray()
}