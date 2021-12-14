package com.example.spasdomuserapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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
    val workerRate: Int,
    val workerInfo: String
): Parcelable {
    val shortDesc: String
        get() = "$date | $time"
}