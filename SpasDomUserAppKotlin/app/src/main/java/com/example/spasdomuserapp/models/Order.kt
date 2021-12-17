package com.example.spasdomuserapp.models

import android.os.Parcelable
import com.example.spasdomuserapp.database.CacheMarketOrder
import com.example.spasdomuserapp.database.CachePlannedOrder
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

@Parcelize
data class Order(
    val id: Int,
    val title: String,
    val date: String,
    val time: String,
    val userRate: Int,
    val userReview: String,
    val status: Int,
    val workerImg: String,
    val workerName: String,
    val workerRate: Double,
    val workerInfo: String
): Parcelable {
    val shortDesc: String
        get() = "$date | $time"
}


data class NetworkOrder(
    val id: Int,
    val title: String?,
    @SerializedName("date")
    val offsetDate: OffsetDateTime,
    val time: String?,
    val userRate: Int,
    val userReview: String?,
    val status: Int,
    val workerImg: String?,
    val workerName: String?,
    val workerRate: Double,
    val workerInfo: String?
) {
    val date: String
        get() = offsetDate.format(DateTimeFormatter.ofPattern("mm.dd.yyyy"))
}


fun List<NetworkOrder>.asCachePlannedModel(): Array<CachePlannedOrder> {
    return map {
        CachePlannedOrder (
            id = it.id,
            title = it.title ?: "",
            date = it.date ?: "",
            time = it.time ?: "",
            userRate = it.userRate,
            userReview = it.userReview ?: "",
            status = it.status,
            workerImg = it.workerImg ?: "",
            workerName = it.workerName ?: "",
            workerRate = it.workerRate,
            workerInfo = it.workerInfo ?: "")
    }.toTypedArray()
}


fun List<NetworkOrder>.asCacheMarketModel(): Array<CacheMarketOrder> {
    return map {
        CacheMarketOrder (
            id = it.id,
            title = it.title ?: "",
            date = it.date ?: "",
            time = it.time ?: "",
            userRate = it.userRate,
            userReview = it.userReview ?: "",
            status = it.status,
            workerImg = it.workerImg ?: "",
            workerName = it.workerName ?: "",
            workerRate = it.workerRate,
            workerInfo = it.workerInfo ?: "")
    }.toTypedArray()
}