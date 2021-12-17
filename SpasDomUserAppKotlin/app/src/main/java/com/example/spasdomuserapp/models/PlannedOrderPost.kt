package com.example.spasdomuserapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.OffsetDateTime

@Parcelize
data class PlannedOrderPost (
    val categoryId: Int,
    val subcategoryId: Int,
    val comment: String = "No comment",
    val timeStart: OffsetDateTime? = null,
    val timeEnd: OffsetDateTime? = null,
    val workerId: List<Int>? = null
) : Parcelable