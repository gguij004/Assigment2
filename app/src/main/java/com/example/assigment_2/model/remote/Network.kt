package com.example.assigment_2.model.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    val api: ItunesService by lazy {
        initRetrofit().create(ItunesService::class.java)
    }

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_ITUNES_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val loginInterceptor = HttpLoggingInterceptor()
        loginInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val client = OkHttpClient.Builder()
            .addInterceptor(loginInterceptor)
            .build()

        return client

    }


}