package com.example.spasdomuserapp.repository

import com.example.spasdomuserapp.database.UserPreferences
import com.example.spasdomuserapp.network.AuthApi
import com.example.spasdomuserapp.responses.AuthUser
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val preferences: UserPreferences
) : BaseRepository(api) {

    suspend fun login(authUser: AuthUser) = safeApiCall {
        api.login(authUser)
    }

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        preferences.saveAccessTokens(accessToken, refreshToken)
    }
}