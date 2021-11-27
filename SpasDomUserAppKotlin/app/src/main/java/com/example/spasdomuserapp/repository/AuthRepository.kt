package com.example.spasdomuserapp.repository

import com.example.spasdomuserapp.database.UserPreferences
import com.example.spasdomuserapp.network.AuthApi
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(login: String, password: String) = safeApiCall {
        api.login(login, password)
    }

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        preferences.saveAccessTokens(accessToken, refreshToken)
    }
}