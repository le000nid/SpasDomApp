package com.example.spasdomuserapp.repository

import com.example.spasdomuserapp.network.BaseApi
import com.example.spasdomuserapp.network.Resource
import com.example.spasdomuserapp.network.SafeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException


abstract class BaseRepository(private val api: BaseApi) : SafeApiCall {

    suspend fun logout() = safeApiCall { api.logout() }
}