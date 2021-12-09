package com.example.spasdomworkerapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.spasdomworkerapp.database.CacheDao
import com.example.spasdomworkerapp.repository.OrderItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.lang.StringBuilder
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: OrderItemsRepository,
): ViewModel() {

    init {
        viewModelScope.launch {
//            repository.refreshOrderItems()
        }
    }

    var date = Calendar.getInstance()
    lateinit var weekday: String
    var OrderGetFormat = SimpleDateFormat("dd-MM-yyyy").format(date.time)
    lateinit var OrderShowFormat: String

    var orderItems = repository.getOrderItems(OrderGetFormat)

    fun swipeToRefresh() = viewModelScope.launch {
//        repository.refreshOrderItems()
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
        orderItems = repository.getOrderItems(OrderGetFormat)
    }
}
