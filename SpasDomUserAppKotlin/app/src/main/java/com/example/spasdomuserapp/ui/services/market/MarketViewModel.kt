package com.example.spasdomuserapp.ui.services.market

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
class MarketViewModel @Inject constructor(
    private val repository: OrdersRepository
): ViewModel() {

    init {
        viewModelScope.launch {
            repository.refreshMarketOrders()
        }
    }

    val activeMarketOrders = repository.activeMarketOrders
    val historyMarketOrders = repository.historyMarketOrders


    fun swipeToRefresh() = viewModelScope.launch {
        repository.refreshMarketOrders()
    }

    private val _marketPutResponse: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    val marketPutResponse: LiveData<Resource<Boolean>>
        get() = _marketPutResponse

    fun putMarketOrder(order: Order) = viewModelScope.launch {
        _marketPutResponse.value = Resource.Loading
        _marketPutResponse.value = repository.putMarketOrder(order)
    }

    fun updateMarketOrder(order: Order) = viewModelScope.launch {
        repository.updateCacheMarketOrder(order)
    }
}