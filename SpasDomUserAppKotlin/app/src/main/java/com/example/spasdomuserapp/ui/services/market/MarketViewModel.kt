package com.example.spasdomuserapp.ui.services.market

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spasdomuserapp.models.Order
import com.example.spasdomuserapp.network.Resource
import com.example.spasdomuserapp.repository.OrdersRepository
import com.example.spasdomuserapp.responses.OrderListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val repository: OrdersRepository
): ViewModel() {

    val activeMarketOrders: LiveData<List<Order>> = repository.activeMarketOrders
    val historyMarketOrders: LiveData<List<Order>> = repository.historyMarketOrders

    /**
     * Network get request. All market orders
     */
    private val _marketOrders: MutableLiveData<Resource<OrderListResponse>> = MutableLiveData()
    val marketOrders: LiveData<Resource<OrderListResponse>>
        get() = _marketOrders

    fun getMarketOrders() = viewModelScope.launch {
        _marketOrders.value = Resource.Loading
        _marketOrders.value = repository.getMarketOrders()
    }

    /**
     * DataBase insert query. All planned orders
     */
    fun insertAllMarketOrdersToCache(orders: OrderListResponse) = viewModelScope.launch {
        repository.insertAllMarketOrdersToCache(orders)
    }


    private val _marketPutResponse: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val marketPutResponse: LiveData<Resource<Boolean>>
        get() = _marketPutResponse

    fun putMarketOrder(order: Order) = viewModelScope.launch {
        _marketPutResponse.value = Resource.Loading
        _marketPutResponse.value = repository.putMarketOrder(order)
    }
}