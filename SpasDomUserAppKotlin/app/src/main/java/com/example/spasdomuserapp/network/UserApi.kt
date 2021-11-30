package com.example.spasdomuserapp.network

import com.example.spasdomuserapp.responses.LoginResponse
import com.example.spasdomuserapp.responses.UserResponse
import retrofit2.http.GET

interface UserApi : BaseApi{
    @GET("profile")
    suspend fun getUser(): UserResponse
}