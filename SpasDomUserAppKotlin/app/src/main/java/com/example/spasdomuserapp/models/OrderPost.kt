package com.example.spasdomuserapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderPost (
    val categoryId: Int,
    val subcategoryId: Int,
    val comment: String = "No comment",
    val date: String = "",
    val workerId: Long? = null
) : Parcelable