package com.example.spasdomuserapp.ui.services.planned.planned

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spasdomuserapp.models.NetworkOrder
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

    val activePlannedOrders: LiveData<List<Order>> = repository.activePlannedOrders
    val historyPlannedOrders: LiveData<List<Order>> = repository.historyPlannedOrders

    /**
     * Network get request. All planned orders
     */
    private val _plannedOrders: MutableLiveData<Resource<List<NetworkOrder>>> = MutableLiveData()
    val plannedOrders: LiveData<Resource<List<NetworkOrder>>>
        get() = _plannedOrders

    fun getPlannedOrders() = viewModelScope.launch {
        _plannedOrders.value = Resource.Loading
        _plannedOrders.value = repository.getPlannedOrders()
    }

    /**
     * DataBase insert query. All planned orders
     */
    fun insertAllPlannedOrdersToCache(orders: List<NetworkOrder>) = viewModelScope.launch {
        repository.insertAllPlannedOrdersToCache(orders)
    }



    private val _plannedPutResponse: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val plannedPutResponse: LiveData<Resource<Boolean>>
        get() = _plannedPutResponse

    fun putPlannedOrder(order: Order) = viewModelScope.launch {
        _plannedPutResponse.value = Resource.Loading
        _plannedPutResponse.value = repository.putPlannedOrder(order)
    }
}