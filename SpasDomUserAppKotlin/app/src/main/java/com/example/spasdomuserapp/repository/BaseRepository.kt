package com.example.spasdomuserapp.repository

import com.example.spasdomuserapp.network.BaseApi
import com.example.spasdomuserapp.network.SafeApiCall


abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

    suspend fun logout() = safeApiCall { api.logout() }
}