package com.example.spasdomuserapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spasdomuserapp.models.Alert
import com.example.spasdomuserapp.models.NewsItem
import com.example.spasdomuserapp.models.PlannedOrder

@Entity
data class DatabaseNewsItem constructor(
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String)

fun List<DatabaseNewsItem>.asDomainModel(): List<NewsItem> {
    return map {
        NewsItem(
                url = it.url,
                title = it.title,
                description = it.description,
                updated = it.updated,
                thumbnail = it.thumbnail)
    }
}

// TODO(Replace primary key)

@Entity
data class DataBaseAlert(
    val data: String,
    val title: String,
    @PrimaryKey
    val description: String
)

fun List<DataBaseAlert>.asDomainAlertModel(): List<Alert> {
    return map {
        Alert(
            data = it.data,
            title = it.title,
            description = it.description
        )
    }
}


@Entity
data class CachePlannedOrder(
    @PrimaryKey
    val id: Int,
    val title: String,
    val date: String,
    val time: String,
    val userRate: Int,
    val userReview: String,
    val isFinished: Boolean,
    val workerImg: String,
    val workerName: String,
    val workerRate: Int,
    val workerInfo: String
)

fun List<CachePlannedOrder>.asDomainPlannedOrder(): List<PlannedOrder> {
    return map {
        PlannedOrder(
            id = it.id,
            title = it.title,
            date = it.date,
            time = it.time,
            userRate = it.userRate,
            userReview = it.userReview,
            isFinished = it.isFinished,
            workerImg = it.workerImg,
            workerName = it.workerName,
            workerRate = it.workerRate,
            workerInfo = it.workerInfo
        )
    }
}

fun PlannedOrder.asCachePlannerOrder(): CachePlannedOrder {
    return CachePlannedOrder(
        id = id,
        title = title,
        date = date,
        time = time,
        userRate = userRate,
        userReview = userReview,
        isFinished = isFinished,
        workerImg = workerImg,
        workerName = workerName,
        workerRate = workerRate,
        workerInfo = workerInfo
    )
}