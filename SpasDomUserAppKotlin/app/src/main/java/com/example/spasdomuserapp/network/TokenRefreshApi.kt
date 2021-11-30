package com.example.spasdomuserapp.network

import com.example.spasdomuserapp.responses.LoginResponse
import com.example.spasdomuserapp.responses.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface TokenRefreshApi : BaseApi {

    @POST("auth/refresh")
    suspend fun refreshAccessToken(): LoginResponse
}