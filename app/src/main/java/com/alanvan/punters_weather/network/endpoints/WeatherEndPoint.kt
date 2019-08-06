package com.alanvan.punters_weather.network.endpoints

import com.alanvan.punters_weather.model.VenueWeatherData
import io.reactivex.Observable
import retrofit2.http.GET

interface WeatherEndPoint {

    companion object {
        const val END_POINT = "http://dnu5embx6omws.cloudfront.net"
    }

    @GET("/venues/weather.json")
    fun loadData(): Observable<List<VenueWeatherData>>
}