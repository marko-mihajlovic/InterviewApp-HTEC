package com.marko.htec.interviewapp.repository

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Created by Marko Mihajlovic on 28.8.2021.
 */
object TestRemoteApi {

    private val TEST_BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(TEST_BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> getService(clsOfService: Class<T>): T {
        return retrofit.create(clsOfService)
    }

}