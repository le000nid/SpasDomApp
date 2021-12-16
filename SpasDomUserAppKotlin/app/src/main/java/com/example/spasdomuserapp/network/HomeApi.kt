package com.example.spasdomuserapp.network

import com.example.spasdomuserapp.models.NetworkAlert
import com.example.spasdomuserapp.responses.NewsResponse
import retrofit2.http.GET

interface HomeApi {

    @GET("/news")
    suspend fun getNews(): NewsResponse

    @GET("/announcements")
    suspend fun getAlerts(): List<NetworkAlert>
}