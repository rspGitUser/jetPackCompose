package com.test.jetpackcompose.data.network

import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        println("--> ${request.method} ${request.url}")

        val response = chain.proceed(request)

        println("<-- ${response.code} ${response.message} ${response.request.url}")

        return response
    }
}