package com.example.spasdomworkerapp.network

import com.example.spasdomworkerapp.responses.AuthUser
import com.example.spasdomworkerapp.responses.LoginResponse
import retrofit2.http.*

interface AuthApi: BaseApi {

    @POST("/auth/login")
    suspend fun login(@Body authUser: AuthUser): LoginResponse
}