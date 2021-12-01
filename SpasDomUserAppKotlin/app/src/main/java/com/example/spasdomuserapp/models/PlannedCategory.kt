package com.example.spasdomuserapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlannedCategory (
    val label: String,
    val drawableId: Int,
    val listLvl2: List<PlannedCategory>?
    ): Parcelable