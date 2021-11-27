package com.example.spasdomworkerapp.ui.home

import android.app.Application
import android.view.View
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
import java.util.*
import androidx.databinding.Bindable




class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val ordersRepository = OrderItemsRepository(database)
    var date: Date = Date()
    var OrderGetFormat = SimpleDateFormat("dd-MM-yyyy").format(date)

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        viewModelScope.launch {
            ordersRepository.refreshOrderItems()
        }
    }

    val orderItems = ordersRepository.getOrderItems(OrderGetFormat)

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

    fun ButtonNext(view: View?) {

    }
}
