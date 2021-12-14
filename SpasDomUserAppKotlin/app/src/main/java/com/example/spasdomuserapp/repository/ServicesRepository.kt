package com.example.spasdomuserapp.repository

import com.example.spasdomuserapp.network.OrderApi
import com.example.spasdomuserapp.network.SafeApiCall
import javax.inject.Inject

class ServicesRepository @Inject constructor(
    private val api: OrderApi
): SafeApiCall {

    suspend fun getPlannedCategories() = safeApiCall { api.getPlannedCategories() }

    suspend fun getMarketCategories() = safeApiCall { api.getMarketCategories() }

    suspend fun getMarketPreviewWorkers() = safeApiCall { api.getMarketPreviewWorkers("") }
}