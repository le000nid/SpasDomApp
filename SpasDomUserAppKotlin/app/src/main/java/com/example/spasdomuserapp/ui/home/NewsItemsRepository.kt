package com.example.spasdomuserapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.spasdomuserapp.database.NewsItemsDatabase
import com.example.spasdomuserapp.database.asDomainAlertModel
import com.example.spasdomuserapp.database.asDomainModel
import com.example.spasdomuserapp.domain.Alert
import com.example.spasdomuserapp.domain.NewsItem
import com.example.spasdomuserapp.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class NewsItemsRepository(private val database: NewsItemsDatabase) {

    /**
     * A feed of newsItems that can be shown on the screen.
     */
    val newsItems: LiveData<List<NewsItem>> =
        Transformations.map(database.dao.getNewsItems()) {
            it.asDomainModel()
        }

    val alerts: LiveData<List<Alert>> =
        Transformations.map(database.dao.getAlerts()) {
            it.asDomainAlertModel()
        }

    /**
     * Refresh the newsItems stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the newsItems for use, observe [newsItems]
     *
     * The Retrofit can't resolve the URL host when there is no connection.
     */
    suspend fun refreshNewsItems() {
        withContext(Dispatchers.IO) {
            try {
                //TODO(When null or empty is received it means that we need update actual data(clear all case))
                val newsItems = Network.spasDom.getNewsItems()
                // Take first three items
                val threeNews = NetworkNewsContainer(newsItems.videos.take(3))

                database.dao.insertAll(*threeNews.asDatabaseModel())
            } catch (e: Exception) {
                Timber.e("refreshVideos() error = %s", e.message)
            }
        }
    }


    suspend fun refreshAlerts() {
        withContext(Dispatchers.IO) {
            try {
                // TODO(val alerts = Network.spasDom.getAlerts())
                val alertsInit: List<NetworkAlerts> = listOf(
                    NetworkAlerts("20 Октбяра с 3 до 8", "Отключение горячей воды","Описание1"),
                    NetworkAlerts("21 Октбяра с 3 до 8", "Отключение Холодной воды","Описание2"),
                )

                val alerts = NetworkAlertsContainer(alertsInit)
                database.dao.insertAllAlerts(*alerts.asDatabaseAlertModel())
            } catch (e: Exception) {
                Timber.e("refreshVideos() error = %s", e.message)
            }
        }
    }
}
