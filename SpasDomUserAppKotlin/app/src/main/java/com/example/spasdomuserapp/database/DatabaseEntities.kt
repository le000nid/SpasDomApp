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
    val url: String,
    val date: String
)

fun List<CacheNewsItem>.asDomainModel(): List<NewsItem> {
    return map {
        NewsItem(
            url = it.url,
            title = it.title,
            description = it.description,
            date = it.date)
    }
}


@Entity
data class CacheAlert(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val data: String,
    val title: String,
    val description: String
)

fun List<CacheAlert>.asDomainAlertModel(): List<Alert> {
    return map {
        Alert(
            id = it.id,
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
    @PrimaryKey
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