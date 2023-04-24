package org.yash10019coder.suspectdetectionxml.data

import okhttp3.OkHttpClient
import org.yash10019coder.suspectdetectionxml.data.Api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    //    const val BASE_URL = "http://192.168.110.116:5000"
//    const val BASE_URL = "http://172.70.111.186:5000"
    const val BASE_URL = "http://192.168.5.116:5000"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
