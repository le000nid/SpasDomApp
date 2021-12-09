package com.example.spasdomworkerapp.responses

data class LoginResponse(
    val access: TokenResponse,
    val refresh: TokenResponse
)