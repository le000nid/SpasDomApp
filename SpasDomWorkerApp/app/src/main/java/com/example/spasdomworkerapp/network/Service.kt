package com.example.spasdomworkerapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

//interface SpasDomService {
//    @GET("devbytes.json")
//    suspend fun getNewsItems(): NetworkOrdersContainer
//
//    @FormUrlEncoded
//    @POST("login")
//    fun tryLoginUser(
//        @Field("businessAccount") login: String,
//        @Field("password") password: String
//    ): Call<Boolean>
//}
//
///**
// * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
// * full Kotlin compatibility.
// */
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//
///**
// * Main entry point for network access. Call like `Network.newsItems.getPlaylist()`
// */
//object Network {
//
//    private val client: OkHttpClient = OkHttpClient.Builder()
//        .addInterceptor(LoggingInterceptor())
//        .build()
//
//    // Configure retrofit to parse JSON and use coroutines
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("http://51.250.24.236/")
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .addCallAdapterFactory(CoroutineCallAdapterFactory())
//        .client(client)
//        .build()
//
//    val spasDom: SpasDomService = retrofit.create(SpasDomService::class.java)
//}
