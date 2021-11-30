package com.example.spasdomuserapp.responses

data class LoginResponse(
    val access: TokenResponse,
    val refresh: TokenResponse
)