package com.alanvan.punters_weather.data.data_source

import com.alanvan.punters_weather.data.model.VenueWeatherData
import io.reactivex.Observable

interface DataSource {

    fun getVenueWeatherData(): Observable<List<VenueWeatherData>>

    fun getVenueWeather(venueId: Int): Observable<VenueWeatherData>

}