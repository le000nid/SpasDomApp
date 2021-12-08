package com.example.spasdomworkerapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(val date: String,
                 val address: String,
                 val time: String,
                 val problem: String,
                 var active: Boolean,
                 var finished: Boolean,
                 val id: Int): Parcelable {
    val shortDesc: String
        get() = "$time | $problem"
}