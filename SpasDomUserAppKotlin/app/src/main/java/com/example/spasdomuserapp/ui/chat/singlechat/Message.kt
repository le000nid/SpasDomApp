package com.example.spasdomuserapp.ui.chat.singlechat

data class Message (
    val text: String= "",
    val type: String="",
    var timeSTAMP: Any = 11,
    val from: String= "",
    val fromName: String=""
)