package com.alanvan.punters_weather.repository

import com.alanvan.punters_weather.model.VenueWeatherData
import io.reactivex.Observable

interface WeatherRepository {

    fun getVenueWeatherData(): Observable<List<VenueWeatherData>>
}