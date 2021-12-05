package com.example.spasdomworkerapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spasdomworkerapp.database.getDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.lang.StringBuilder
import java.util.*


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val ordersRepository = OrderItemsRepository(database)
    var date = Calendar.getInstance()
    lateinit var weekday: String
    var OrderGetFormat = SimpleDateFormat("dd-MM-yyyy").format(date.time)
    lateinit var OrderShowFormat: String
    var orderItems = ordersRepository.getOrderItems(OrderGetFormat)

    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        viewModelScope.launch {
            ordersRepository.refreshOrderItems()
        }
    }

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
        changeDate()

    }

    fun PreviousDay(){
        date.add(Calendar.DATE, -1)
        changeDate()

    }

    fun SomeDay(c : Calendar){
        date = c
        changeDate()
    }

    fun changeDate(){
        OrderGetFormat = SimpleDateFormat("dd-MM-yyyy").format(date.time)
        when(date.get(Calendar.DAY_OF_WEEK)){
            1 -> {
                weekday = "Понедельник"
            }
            2 -> {
                weekday = "Вторник"
            }
            3 -> {
                weekday = "Среда"
            }
            4 -> {
                weekday = "Четверг"
            }
            5 -> {
                weekday = "Пятница"
            }
            6 -> {
                weekday = "Суббота"
            }
            7 -> {
                weekday = "Воскресение"
            }
        }
        OrderShowFormat = StringBuilder().append(weekday).append(SimpleDateFormat(". dd.MM.yyyy").format(date.time))
            .toString()
        orderItems = ordersRepository.getOrderItems(OrderGetFormat)
    }
}
