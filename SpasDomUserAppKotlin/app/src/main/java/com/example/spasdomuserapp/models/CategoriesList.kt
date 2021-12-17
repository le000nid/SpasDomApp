package com.example.spasdomuserapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoriesList (
    val title: String,
    val categoryId: Int,
    @SerializedName("iconUrl")
    val drawableUrl: String,
    val subcategory: List<CategoriesList>? = null
): Parcelable