package com.example.spasdomuserapp.ui.chat


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Chat (
    val name: String ="",
    val uid: String = ""
    //val photo: Bitmap
    ): Parcelable