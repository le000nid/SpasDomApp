package com.example.spasdomuserapp.ui.services.planned.planned

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spasdomuserapp.models.PlannedOrder
import com.example.spasdomuserapp.repository.PlannedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlannedViewModel@Inject constructor(
    private val repository: PlannedRepository
): ViewModel() {

    init {
        viewModelScope.launch {
            repository.refreshPlannedOrders()
        }
    }

    val activePlannedOrders = repository.activePlannedOrders
    val historyPlannedOrders = repository.historyPlannedOrders


    fun swipeToRefresh() = viewModelScope.launch {
        repository.refreshPlannedOrders()
    }

    fun updatePlannedOrder(newPlannedOrder: PlannedOrder) = viewModelScope.launch {
        repository.updatePlannedOrder(newPlannedOrder)
    }
}