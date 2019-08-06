package com.alanvan.punters_weather.repository

object RepositoryManager {

    private val weatherRepository = WeatherRepositoryImpl()

    fun getWeatherRepository(): WeatherRepository {
        return weatherRepository
    }

}