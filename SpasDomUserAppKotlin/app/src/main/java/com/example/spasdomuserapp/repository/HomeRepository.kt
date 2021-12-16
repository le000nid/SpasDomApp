package com.example.spasdomuserapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.spasdomuserapp.database.CacheDao
import com.example.spasdomuserapp.database.asDomainAlertModel
import com.example.spasdomuserapp.database.asDomainModel
import com.example.spasdomuserapp.models.Alert
import com.example.spasdomuserapp.models.NetworkAlert
import com.example.spasdomuserapp.models.NewsItem
import com.example.spasdomuserapp.models.asCacheAlertsModel
import com.example.spasdomuserapp.network.HomeApi
import com.example.spasdomuserapp.network.SafeApiCall
import com.example.spasdomuserapp.responses.NewsResponse
import com.example.spasdomuserapp.responses.asCacheNewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val cacheDao: CacheDao,
    private val api: HomeApi
): SafeApiCall {

    val newsItems: LiveData<List<NewsItem>> =
        Transformations.map(cacheDao.getNewsItems()) {
            it.asDomainModel()
        }

    suspend fun getNews() = safeApiCall { api.getNews() }

    suspend fun insertAllNewsToCache(news: NewsResponse) {
        withContext(Dispatchers.IO) {
            cacheDao.insertAllNews(*news.asCacheNewsModel())
        }
    }


    val alerts: LiveData<List<Alert>> =
        Transformations.map(cacheDao.getAlerts()) {
            it.asDomainAlertModel()
        }

    suspend fun getAlerts() = safeApiCall { api.getAlerts() }

    suspend fun insertAllAlertsToCache(alerts: List<NetworkAlert>) {
        withContext(Dispatchers.IO) {
            cacheDao.insertAllAlerts(*alerts.asCacheAlertsModel())
        }
    }

    suspend fun deleteAllAlerts() {
        withContext(Dispatchers.IO) {
            cacheDao.deleteAllAlerts()
        }
    }
}
