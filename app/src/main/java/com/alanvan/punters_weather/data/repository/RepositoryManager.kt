package com.alanvan.punters_weather.data.repository

object RepositoryManager {

    private val weatherRepository = WeatherRepositoryImpl()

    fun getWeatherRepository(): WeatherRepository {
        return weatherRepository
    }

}