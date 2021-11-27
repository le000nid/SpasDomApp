package com.example.spasdomuserapp.repository

import com.example.spasdomuserapp.network.AuthApi
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val api: AuthApi
) : BaseRepository() {

    suspend fun login(login: String, password: String) = safeApiCall {
        api.login(login, password)
    }
}