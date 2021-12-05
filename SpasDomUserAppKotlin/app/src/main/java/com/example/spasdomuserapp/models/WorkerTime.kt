package com.example.spasdomuserapp.models

data class WorkerTime(
    val timeStart: String,
    val timeEnd: String,
    val workerId: Long) {
    var time = "$timeStart - $timeEnd"
}
