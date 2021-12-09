package com.example.spasdomworkerapp.network

import com.example.spasdomworkerapp.responses.UserResponse
import retrofit2.http.GET

interface UserApi : BaseApi{
    @GET("profile")
    suspend fun getUser(): UserResponse
}