package com.example.spasdomworkerapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order(val date: String,
                 val address: String,
                 val time: String,
                 val problem: String,
                 val finished: Boolean,
                 val canceled: Boolean,
                 val id: Int): Parcelable{
    val shortDesc: String
        get() = "$time | $problem"
}