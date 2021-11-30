package com.example.spasdomworkerapp.ui.home

import android.annotation.SuppressLint
import android.app.Application
import android.text.format.DateUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spasdomworkerapp.R
import com.example.spasdomworkerapp.database.getDatabase
import com.example.spasdomworkerapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import androidx.databinding.Bindable
import java.util.*


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val ordersRepository = OrderItemsRepository(database)
    var date = Calendar.getInstance()
    var OrderGetFormat = SimpleDateFormat("dd-MM-yyyy").format(date.time)

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        viewModelScope.launch {
            ordersRepository.refreshOrderItems()
        }
    }

    var orderItems = ordersRepository.getOrderItems(OrderGetFormat)

    fun swipeToRefresh() = viewModelScope.launch {
        ordersRepository.refreshOrderItems()
    }

    /**
     * Factory for constructing HomeViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }

    fun NextDay(){
        date.add(Calendar.DATE, 1)
        OrderGetFormat = SimpleDateFormat("dd-MM-yyyy").format(date.time)
        orderItems = ordersRepository.getOrderItems(OrderGetFormat)
    }

    fun PreviousDay(){
        date.add(Calendar.DATE, -1)
        OrderGetFormat = SimpleDateFormat("dd-MM-yyyy").format(date.time)
        orderItems = ordersRepository.getOrderItems(OrderGetFormat)
    }

    fun SomeDay(c : Calendar){
        date = c
        OrderGetFormat = SimpleDateFormat("dd-MM-yyyy").format(date.time)
        orderItems = ordersRepository.getOrderItems(OrderGetFormat)
    }
}
