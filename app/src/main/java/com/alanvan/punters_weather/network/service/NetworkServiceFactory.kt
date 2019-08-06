package com.alanvan.punters_weather.network.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkServiceFactory {

    private val LOCK = Object()

    fun <T> createService(serviceClass: Class<T>, endPoint: String): T {

        return synchronized(LOCK) {
            val client = OkHttpClient.Builder().build()

            val retrofit = Retrofit.Builder().baseUrl(endPoint)
                .client(client).addConverterFactory(
                    GsonConverterFactory
                        .create()
                ).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()

            return@synchronized retrofit.create(serviceClass)
        }

    }
}