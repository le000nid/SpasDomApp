package com.example.spasdomuserapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.spasdomuserapp.database.NewsItemsDatabase
import com.example.spasdomuserapp.database.asDomainModel
import com.example.spasdomuserapp.domain.NewsItem
import com.example.spasdomuserapp.network.Network
import com.example.spasdomuserapp.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class NewsItemsRepository(private val database: NewsItemsDatabase) {

    /**
     * A feed of newsItems that can be shown on the screen.
     */
    val newsItems: LiveData<List<NewsItem>> =
        Transformations.map(database.newsItemsDao.getNewsItems()) {
            it.asDomainModel()
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
                val newsItems = Network.spasDom.getNewsItems()
                database.newsItemsDao.insertAll(*newsItems.asDatabaseModel())
            } catch (e: Exception) {
                Timber.e("refreshVideos() error = %s", e.message)
            }
        }
    }
}
