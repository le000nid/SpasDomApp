package com.example.spasdomuserapp.ui.services.planned.categories.date

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.spasdomuserapp.R
import com.example.spasdomuserapp.models.*

class DatePlannedOrderViewModel(
    private val state: SavedStateHandle
): ViewModel() {

    var date: String? = null
    var time: String? = null


    private val timeList1: List<WorkerTime> = listOf(
        WorkerTime("8:00", "9:00", 1),
        WorkerTime("9:00", "10:00", 1),
        WorkerTime("10:00", "11:00", 1),
        WorkerTime("11:00", "12:00", 1)
        )

    private val timeList2: List<WorkerTime> = listOf(
        WorkerTime("13:00", "14:00", 1),
        WorkerTime("14:00", "15:00", 1),
        WorkerTime("15:00", "16:00", 1),
        WorkerTime("16:00", "17:00", 1),
        WorkerTime("17:00", "18:00", 1),
    )

    private val listWorkerDays: List<WorkerDay> = listOf(
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("1", 0,null),
        WorkerDay("2", 0,null),
        WorkerDay("3", 1,timeList1),
        WorkerDay("4", 2,timeList2),
        WorkerDay("5", 0,null),
        WorkerDay("6", 0,null),
        WorkerDay("7", 1,timeList1),
        WorkerDay("8", 0,null),
        WorkerDay("9", 1,timeList2),
        WorkerDay("10", 2,timeList2),
        WorkerDay("11", 0,null),
        WorkerDay("12", 1,timeList1),
        WorkerDay("13", 0,null),
        WorkerDay("14", 2,timeList2),
        WorkerDay("15", 1,timeList1),
        WorkerDay("16", 0,null),
        WorkerDay("17", 1,timeList1),
        WorkerDay("18", 0,null),
        WorkerDay("19", 0,null),
        WorkerDay("20", 1,timeList1),
        WorkerDay("21", 2,timeList2),
        WorkerDay("22", 0,null),
        WorkerDay("23", 1,timeList1),
        WorkerDay("24", 0,null),
        WorkerDay("25", 2,timeList2),
        WorkerDay("26", 0,null),
        WorkerDay("27", 1,timeList1),
        WorkerDay("28", 0,null),
        WorkerDay("29", 1,timeList2),
        WorkerDay("30", 2,timeList2),
        WorkerDay("31", 2,timeList2),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
    )

    private val listWorkerDays2: List<WorkerDay> = listOf(
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("1", 0,null),
        WorkerDay("2", 0,null),
        WorkerDay("3", 1,timeList1),
        WorkerDay("4", 2,timeList2),
        WorkerDay("5", 0,null),
        WorkerDay("6", 0,null),
        WorkerDay("7", 1,timeList1),
        WorkerDay("8", 0,null),
        WorkerDay("9", 1,timeList2),
        WorkerDay("10", 2,timeList2),
        WorkerDay("11", 0,null),
        WorkerDay("12", 2,timeList1),
        WorkerDay("13", 0,null),
        WorkerDay("14", 1,timeList2),
        WorkerDay("15", 2,timeList1),
        WorkerDay("16", 0,null),
        WorkerDay("17", 2,timeList1),
        WorkerDay("18", 0,null),
        WorkerDay("19", 0,null),
        WorkerDay("20", 1,timeList1),
        WorkerDay("21", 2,timeList2),
        WorkerDay("22", 0,null),
        WorkerDay("23", 1,timeList1),
        WorkerDay("24", 0,null),
        WorkerDay("25", 2,timeList2),
        WorkerDay("26", 0,null),
        WorkerDay("27", 2,timeList1),
        WorkerDay("28", 0,null),
        WorkerDay("29", 2,timeList2),
        WorkerDay("30", 2,timeList2),
        WorkerDay("31", 1,timeList2),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
        WorkerDay("", 0,null),
    )

    val workerMonth: List<WorkerMonth> = listOf(
        WorkerMonth("December 2021", listWorkerDays),
        WorkerMonth("January 2022", listWorkerDays2)
    )

}