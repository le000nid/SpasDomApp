package com.example.spasdomuserapp.models

import android.os.Parcelable
import com.example.spasdomuserapp.util.smartTruncate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsItem(val title: String,
                    val description: String,
                    val url: String,
                    val updated: String,
                    val thumbnail: String): Parcelable {

    /**
     * Short description is used for displaying truncated descriptions in the UI
     */
    val shortDescription: String
        get() = description.smartTruncate(200)
}