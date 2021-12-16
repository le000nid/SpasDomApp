package com.example.spasdomuserapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spasdomuserapp.models.Alert
import com.example.spasdomuserapp.models.NewsItem
import com.example.spasdomuserapp.models.Order

@Entity
data class CacheNewsItem constructor(
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String)

fun List<CacheNewsItem>.asDomainModel(): List<NewsItem> {
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
data class CacheAlert(
    val data: String,
    val title: String,
    @PrimaryKey
    val description: String
)

fun List<CacheAlert>.asDomainAlertModel(): List<Alert> {
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
    val status: Int,
    val workerImg: String,
    val workerName: String,
    val workerRate: Int,
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

fun Order.asCachePlannerOrder(): CachePlannedOrder {
    return CachePlannedOrder(
        id = id,
        title = title,
        date = date,
        time = time,
        userRate = userRate,
        userReview = userReview,
        status = status,
        workerImg = workerImg,
        workerName = workerName,
        workerRate = workerRate,
        workerInfo = workerInfo
    )
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
    val workerRate: Int,
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

fun Order.asCacheMarketOrder(): CacheMarketOrder {
    return CacheMarketOrder(
        id = id,
        title = title,
        date = date,
        time = time,
        userRate = userRate,
        userReview = userReview,
        status = status,
        workerImg = workerImg,
        workerName = workerName,
        workerRate = workerRate,
        workerInfo = workerInfo
    )
}