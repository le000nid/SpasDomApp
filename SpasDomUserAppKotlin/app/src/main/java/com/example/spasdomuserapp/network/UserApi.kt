package com.example.spasdomuserapp.network

import com.example.spasdomuserapp.responses.LoginResponse
import retrofit2.http.GET

interface UserApi : BaseApi{
    @GET("user")
    suspend fun getUser(): LoginResponse
}