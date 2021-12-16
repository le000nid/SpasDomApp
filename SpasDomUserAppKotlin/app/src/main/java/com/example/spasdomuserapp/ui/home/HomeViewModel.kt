package com.example.spasdomuserapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spasdomuserapp.models.Alert
import com.example.spasdomuserapp.models.NetworkAlert
import com.example.spasdomuserapp.models.NewsItem
import com.example.spasdomuserapp.network.Resource
import com.example.spasdomuserapp.repository.HomeRepository
import com.example.spasdomuserapp.responses.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {

    val newsItems: LiveData<List<NewsItem>> = repository.newsItems
    val alertsItems: LiveData<List<Alert>> = repository.alerts


    /**
     * Network get request. All news.
     */
    private val _news: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val news: LiveData<Resource<NewsResponse>>
        get() = _news

    fun getNews() = viewModelScope.launch {
        _news.value = Resource.Loading
        _news.value = repository.getNews()
    }

    /**
     * DataBase insert query. All news
     */
    fun insertAllNewsToCache(news: NewsResponse) = viewModelScope.launch {
        repository.insertAllNewsToCache(news)
    }


    /**
     * Network get request. All alerts.
     */
    private val _alerts: MutableLiveData<Resource<List<NetworkAlert>>> = MutableLiveData()
    val alerts: LiveData<Resource<List<NetworkAlert>>>
        get() = _alerts

    fun getAlerts() = viewModelScope.launch {
        _alerts.value = Resource.Loading
        _alerts.value = repository.getAlerts()
    }

    /**
     * DataBase insert query. All alerts
     */
    fun insertAllAlertsToCache(alerts: List<NetworkAlert>) = viewModelScope.launch {
        repository.insertAllAlertsToCache(alerts)
    }

    fun deleteAllAlerts() = viewModelScope.launch {
        repository.deleteAllAlerts()
    }
}
