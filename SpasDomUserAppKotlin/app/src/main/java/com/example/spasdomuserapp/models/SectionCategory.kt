package com.example.spasdomuserapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SectionCategory (
    val title: String,
    val categoryId: Int,
    val drawableUrl: String,
):Parcelable