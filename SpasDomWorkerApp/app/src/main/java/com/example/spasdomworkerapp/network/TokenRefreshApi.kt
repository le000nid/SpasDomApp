package com.example.spasdomworkerapp.network

import com.example.spasdomworkerapp.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface TokenRefreshApi : BaseApi {

    @POST("auth/refresh")
    suspend fun refreshAccessToken(): LoginResponse
}