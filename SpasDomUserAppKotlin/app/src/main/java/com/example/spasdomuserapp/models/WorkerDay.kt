package com.example.spasdomuserapp.models

data class WorkerDay (
    val day: String,
    val type: Int,
    val timesList: List<WorkerTime>?)