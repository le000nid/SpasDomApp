package com.example.spasdomuserapp.ui.services.planned

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spasdomuserapp.database.getDatabase
import kotlinx.coroutines.launch

class PlannedViewModel(application: Application): AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val plannedRepository = PlannedRepository(database)

    init {
        viewModelScope.launch {
            plannedRepository.refreshActivePlannedOrders()
            plannedRepository.refreshHistoryPlannedOrders()
        }
    }

    val activePlannedOrders = plannedRepository.activePlannedOrders

    val historyPlannedOrders = plannedRepository.historyPlannedOrders


    fun swipeToRefresh() = viewModelScope.launch {
        plannedRepository.refreshActivePlannedOrders()
        plannedRepository.refreshHistoryPlannedOrders()
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PlannedViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PlannedViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }
}