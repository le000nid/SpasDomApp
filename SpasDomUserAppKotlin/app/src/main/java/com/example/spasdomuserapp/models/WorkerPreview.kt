package com.example.spasdomuserapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WorkerPreview (
    val id: Int,
    val firstName: String,
    val lastName: String,
    val photo: String,
    val averageCost: String,
    var rate: Int,
    val experience: String,
): Parcelable {
    val name: String
        get() = "$firstName $lastName"
}