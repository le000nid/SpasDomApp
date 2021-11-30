package com.example.spasdomuserapp.responses

data class AuthUser(
    val businessAccount: String,
    val password: String,
    val firebaseToken: String
    )