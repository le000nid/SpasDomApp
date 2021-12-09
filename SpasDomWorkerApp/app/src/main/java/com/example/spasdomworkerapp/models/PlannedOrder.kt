package com.example.spasdomworkerapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class PlannedOrder(
    val id: Int,
    val title: String,
    val date: String,
    val userRate: Int,
    val userReview: String,
    val status: Int,
    val statusName: String,
    val workerImg: String,
    val workerRate: Int,
    val workerInfo: String
)