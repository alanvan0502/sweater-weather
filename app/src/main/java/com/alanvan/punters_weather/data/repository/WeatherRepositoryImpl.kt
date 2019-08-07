package com.alanvan.punters_weather.data.repository

import com.alanvan.punters_weather.data.data_source.LocalDataSource
import com.alanvan.punters_weather.data.data_source.RemoteDataSource
import com.alanvan.punters_weather.data.model.Country
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

    override fun onGetWeatherDataSortedByAlphabet(sortedAscending: Boolean): Observable<List<VenueWeatherData>> {
        return localDataSource.onGetWeatherDataSortedByAlphabet(sortedAscending)
    }

    override fun onGetWeatherDataSortedByTemperature(sortedAscending: Boolean): Observable<List<VenueWeatherData>> {
        return localDataSource.onGetWeatherDataSortedByTemperature(sortedAscending)
    }

    override fun onGetWeatherDataSortedByLastUpdated(sortedAscending: Boolean): Observable<List<VenueWeatherData>> {
        return localDataSource.onGetWeatherDataSortedByLastUpdated(sortedAscending)
    }

    override fun onGetAllCountries(): Observable<List<Country>> {
        return localDataSource.onGetAllCountries()
    }

}