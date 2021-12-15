package com.example.spasdomuserapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Worker(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val photo: String,
    var rate: Int,
    val info: String,
    val experience: String,
    val phone: String,
    val services: List<WorkerService>,
    val reviews: List<WorkerReview>
): Parcelable {
    val fullName: String
        get() = "$firstName $lastName"
}

@Parcelize
data class WorkerService(
    val title: String,
    val price: String
): Parcelable

@Parcelize
data class WorkerReview(
    val firstName: String,
    val lastName: String,
    val reviewDate: String,
    val rate: Int,
    val picture: String,
    val comment: String
): Parcelable {
    val fullName: String
        get() = "$firstName $lastName"
}