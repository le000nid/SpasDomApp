package com.example.spasdomuserapp.network

import android.content.Context
import androidx.viewbinding.BuildConfig
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {

    companion object {
        private const val BASE_URL = "http://51.250.24.236/"
    }


    fun <Api> buildApi(
        api: Class<Api>,
        context: Context
    ): Api {
        //   val authenticator = TokenAuthenticator(context, buildTokenApi())
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().also { client ->
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}