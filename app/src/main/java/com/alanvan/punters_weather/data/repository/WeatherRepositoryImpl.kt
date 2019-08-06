package com.alanvan.punters_weather.data.repository

import com.alanvan.punters_weather.data.data_source.LocalDataSource
import com.alanvan.punters_weather.data.data_source.RemoteDataSource
import com.alanvan.punters_weather.data.model.VenueWeatherData
import io.reactivex.Observable

class WeatherRepositoryImpl: WeatherRepository {

    private val remoteDataSource = RemoteDataSource.getInstance()
    private val localDataSource = LocalDataSource.getInstance()

    override fun syncWeatherData(): Observable<Boolean> {
        return remoteDataSource.syncWeatherData().map {
            localDataSource.saveWeatherData(it)
            return@map true
        }
    }

    override fun onGetWeatherDataByAlphabet(sortedAscending: Boolean): Observable<List<VenueWeatherData>> {
        return localDataSource.onGetWeatherDataByAlphabet(sortedAscending)
    }

    override fun onGetWeatherDataByTemperature(sortedAscending: Boolean): Observable<List<VenueWeatherData>> {
        return localDataSource.onGetWeatherDataByTemperature(sortedAscending)
    }

    override fun onGetWeatherDataByLastUpdated(sortedAscending: Boolean): Observable<List<VenueWeatherData>> {
        return localDataSource.onGetWeatherDataByLastUpdated(sortedAscending)
    }

}