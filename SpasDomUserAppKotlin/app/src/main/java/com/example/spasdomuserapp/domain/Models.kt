package com.example.spasdomuserapp.domain

import android.os.Parcelable
import androidx.lifecycle.Transformations.map
import androidx.room.PrimaryKey
import com.example.spasdomuserapp.database.CachePlannedOrder
import com.example.spasdomuserapp.util.smartTruncate
import kotlinx.android.parcel.Parcelize

/**
 * Domain objects are plain Kotlin data classes that represent the things in our app. These are the
 * objects that should be displayed on screen, or manipulated by the app.
 *
 * @see database for objects that are mapped to the database
 * @see network for objects that parse or prepare network calls
 */

/**
 * News represent a newsItem that can be opened.
 */
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

data class Alert(
    val data: String,
    val title: String,
    val description: String
)

data class PlannedOrder(
    val id: Int,
    val title: String,
    val date: String,
    val time: String,
    val userRate: Int,
    val userReview: String?,
    val isFinished: Boolean,
    val workerImg: String,
    val workerName: String,
    val workerRate: Int,
    val workerInfo: String
) {
    val shortDesc: String
        get() = "$date | $time"
}