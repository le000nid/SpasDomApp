package com.example.spasdomworkerapp.repository

import com.example.spasdomworkerapp.network.BaseApi
import com.example.spasdomworkerapp.network.SafeApiCall


abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

    suspend fun logout() = safeApiCall { api.logout() }
}