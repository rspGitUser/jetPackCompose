package com.test.jetpackcompose.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.test.jetpackcompose.BuildConfig
object ApiClient {
    //private const val BASE_URL = "https://reqres.in/api/"



    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}