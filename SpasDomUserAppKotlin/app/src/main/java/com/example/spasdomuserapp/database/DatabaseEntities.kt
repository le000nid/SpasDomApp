package com.example.spasdomuserapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spasdomuserapp.models.Alert
import com.example.spasdomuserapp.models.NewsItem
import com.example.spasdomuserapp.models.Order

@Entity
data class CacheNewsItem constructor(
    val title: String,
    val description: String,
    @PrimaryKey
    val photoUrl: String,
    val createdAt: String
)

fun List<CacheNewsItem>.asDomainModel(): List<NewsItem> {
    return map {
        NewsItem(
            photoUrl = it.photoUrl,
            title = it.title,
            description = it.description,
            createdAt = it.createdAt)
    }
}


@Entity
data class CacheAlert(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val date: String,
    val title: String,
    val body: String
)

fun List<CacheAlert>.asDomainAlertModel(): List<Alert> {
    return map {
        Alert(
            id = it.id,
            date = it.date,
            title = it.title,
            body = it.body
        )
    }
}


@Entity
data class CachePlannedOrder(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val date: String,
    val time: String,
    val userRate: Int,
    val userReview: String,
    val status: Int,
    val workerImg: String,
    val workerName: String,
    val workerRate: Double,
    val workerInfo: String
)

fun List<CachePlannedOrder>.asDomainPlannedOrder(): List<Order> {
    return map {
        Order(
            id = it.id,
            title = it.title,
            date = it.date,
            time = it.time,
            userRate = it.userRate,
            userReview = it.userReview,
            status = it.status,
            workerImg = it.workerImg,
            workerName = it.workerName,
            workerRate = it.workerRate,
            workerInfo = it.workerInfo
        )
    }
}


@Entity
data class CacheMarketOrder(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val date: String,
    val time: String,
    val userRate: Int,
    val userReview: String,
    val status: Int,
    val workerImg: String,
    val workerName: String,
    val workerRate: Double,
    val workerInfo: String
)

fun List<CacheMarketOrder>.asDomainMarketOrder(): List<Order> {
    return map {
        Order(
            id = it.id,
            title = it.title,
            date = it.date,
            time = it.time,
            userRate = it.userRate,
            userReview = it.userReview,
            status = it.status,
            workerImg = it.workerImg,
            workerName = it.workerName,
            workerRate = it.workerRate,
            workerInfo = it.workerInfo
        )
    }
}