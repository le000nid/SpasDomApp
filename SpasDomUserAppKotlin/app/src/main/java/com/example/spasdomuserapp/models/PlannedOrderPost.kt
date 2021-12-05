package com.example.spasdomuserapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlannedOrderPost (
    val categoryLvl1: String,
    val categoryLvl2: String,
    val comment: String = "No comments",
    val date: String = "",
    val workerId: Long? = null
) : Parcelable