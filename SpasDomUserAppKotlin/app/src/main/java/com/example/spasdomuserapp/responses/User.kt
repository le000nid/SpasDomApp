package com.example.spasdomuserapp.responses

data class User(
    val access_token: String?,
    val refresh_token: String?,
    val id: Int,
    val apartment_number: Int,
    val street: String,
    val building: Int,
    val city: String,
    val personal_account: String,
    val full_name: String,
    val phone_number: String
)