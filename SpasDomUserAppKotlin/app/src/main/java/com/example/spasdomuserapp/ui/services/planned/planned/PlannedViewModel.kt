package com.example.spasdomuserapp.ui.services.planned.planned

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spasdomuserapp.database.CacheDao
import com.example.spasdomuserapp.models.PlannedOrder
import com.example.spasdomuserapp.repository.PlannedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlannedViewModel@Inject constructor(
    private val cacheDao: CacheDao,
): ViewModel() {

    private val plannedRepository = PlannedRepository(cacheDao)

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

    fun updatePlannedOrder(newPlannedOrder: PlannedOrder) = viewModelScope.launch {
        plannedRepository.updatePlannedOrder(newPlannedOrder)
    }
}