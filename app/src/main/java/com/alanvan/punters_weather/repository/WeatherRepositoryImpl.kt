package com.alanvan.punters_weather.repository

import com.alanvan.punters_weather.model.VenueWeatherData
import io.reactivex.Observable

class WeatherRepositoryImpl: WeatherRepository {

    override fun getVenueWeatherData(): Observable<List<VenueWeatherData>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}