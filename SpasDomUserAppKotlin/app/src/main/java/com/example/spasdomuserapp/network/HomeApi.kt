package com.example.spasdomuserapp.network

import com.example.spasdomuserapp.responses.AlertsResponse
import com.example.spasdomuserapp.responses.NewsResponse
import retrofit2.http.GET

interface HomeApi {

    @GET("/news")
    suspend fun getNews(): NewsResponse

    @GET("/alerts")
    suspend fun getAlerts(): AlertsResponse
}