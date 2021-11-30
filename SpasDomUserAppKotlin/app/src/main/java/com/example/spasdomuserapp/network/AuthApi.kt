package com.example.spasdomuserapp.network

import com.example.spasdomuserapp.responses.AuthUser
import com.example.spasdomuserapp.responses.LoginResponse
import retrofit2.http.*

interface AuthApi: BaseApi {

    @POST("/auth/login")
    suspend fun login(@Body authUser: AuthUser): LoginResponse
}