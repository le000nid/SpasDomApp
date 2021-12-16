package com.example.spasdomuserapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarketOrderPost (
    val categoryId: Int,
    val comment: String = "No comment",
    val date: String = "",
    val workerId: Int
) : Parcelable