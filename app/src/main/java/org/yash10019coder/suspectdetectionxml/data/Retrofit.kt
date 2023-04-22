package org.yash10019coder.suspectdetectionxml.data

import okhttp3.OkHttpClient
import org.yash10019coder.suspectdetectionxml.data.Api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private const val BASE_URL = "https://192.168.1.125:500"

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}
