package com.alanvan.punters_weather.data.repository

import com.alanvan.punters_weather.data.model.VenueWeatherData
import io.reactivex.Observable

interface WeatherRepository {

    fun syncWeatherData(): Observable<Boolean>

    fun onGetWeatherDataByAlphabet(sortedAscending: Boolean): Observable<List<VenueWeatherData>>

    fun onGetWeatherDataByTemperature(sortedAscending: Boolean): Observable<List<VenueWeatherData>>

    fun onGetWeatherDataByLastUpdated(sortedAscending: Boolean): Observable<List<VenueWeatherData>>
}