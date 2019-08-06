package com.alanvan.punters_weather.data.repository

import com.alanvan.punters_weather.data.model.VenueWeatherData
import io.reactivex.Observable

interface WeatherRepository {

    fun getVenueWeatherData(): Observable<List<VenueWeatherData>>
}