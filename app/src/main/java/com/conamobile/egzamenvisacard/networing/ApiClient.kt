package com.conamobile.egzamenvisacard.networing

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    var BASE_URL = "https://6232b1828364d63035c1c833.mockapi.io/api/"
    fun getRetrofit(): Retrofit {
        val build = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return build
    }


    //odatda shu ishlatiladi

    fun <T> createService(service: Class<T>): T {
        return getRetrofit().create(service)
    }





}