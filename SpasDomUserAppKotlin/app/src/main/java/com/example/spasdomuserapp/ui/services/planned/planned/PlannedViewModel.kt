package com.example.spasdomuserapp.ui.services.planned.planned

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spasdomuserapp.models.Order
import com.example.spasdomuserapp.network.Resource
import com.example.spasdomuserapp.repository.OrdersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlannedViewModel@Inject constructor(
    private val repository: OrdersRepository
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


    private val _plannedPutResponse: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val plannedPutResponse: LiveData<Resource<Boolean>>
        get() = _plannedPutResponse

    fun putPlannedOrder(order: Order) = viewModelScope.launch {
        _plannedPutResponse.value = Resource.Loading
        _plannedPutResponse.value = repository.putPlannedOrder(order)
    }

    fun updatePlannedOrder(order: Order) = viewModelScope.launch {
        repository.updateCachePlannedOrder(order)
    }
}