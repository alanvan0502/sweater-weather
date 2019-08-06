package com.alanvan.punters_weather.data.repository

import com.alanvan.punters_weather.data.data_source.RemoteDataSource
import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.injection.Injector
import io.reactivex.Observable

class WeatherRepositoryImpl: WeatherRepository {

    private val remoteDataSource = RemoteDataSource.getInstance()

    override fun getVenueWeatherData(): Observable<List<VenueWeatherData>> {
        return remoteDataSource.getVenueWeatherData()
    }

}