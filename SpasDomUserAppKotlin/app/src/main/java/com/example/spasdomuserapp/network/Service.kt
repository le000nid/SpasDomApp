package com.example.spasdomuserapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

// Since we only have one service, this can all go in one file.
// If you add more services, split this to multiple files and make sure to share the retrofit
// object between services.

/**
 * A retrofit service to fetch a newsItems playlist.
 */
interface SpasDomService {
 //   @GET("devbytes.json")
   // suspend fun getNewsItems(): NetworkNewsContainer

    @Headers("Content-Type: application/json")
    @POST("login")
    fun tryLoginUser(@Body loginObject: LoginObject): Call<Boolean>
}

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Main entry point for network access. Call like `Network.newsItems.getPlaylist()`
 */
object Network {

    private fun configure(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(LoggingInterceptor())
            .build()

        // Configure retrofit to parse JSON and use coroutines
        val retrofit = Retrofit.Builder()
            .baseUrl("http://51.250.24.236/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build();

        return retrofit;
    }


    val retrofit = this.configure();
    val spasDom: SpasDomService = retrofit.create(SpasDomService::class.java)
}
