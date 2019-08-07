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

    override fun onGetWeatherDataSortedByAlphabet(sortedAscending: Boolean, countryId: String?): Observable<List<VenueWeatherData>> {
        return localDataSource.onGetWeatherDataSortedByAlphabet(sortedAscending, countryId)
    }

    override fun onGetWeatherDataSortedByTemperature(sortedAscending: Boolean, countryId: String?): Observable<List<VenueWeatherData>> {
        return localDataSource.onGetWeatherDataSortedByTemperature(sortedAscending, countryId)
    }

    override fun onGetWeatherDataSortedByLastUpdated(sortedAscending: Boolean, countryId: String?): Observable<List<VenueWeatherData>> {
        return localDataSource.onGetWeatherDataSortedByLastUpdated(sortedAscending, countryId)
    }

    override fun onGetWeatherData(): Observable<List<VenueWeatherData>> {
        return localDataSource.onGetWeatherData()
    }

    override fun onGetAllCountries(): Observable<List<Country>> {
        return localDataSource.onGetAllCountries()
    }

    override fun onGetWeatherDataByCountryId(countryId: String?): Observable<List<VenueWeatherData>> {
        return if (countryId == null) {
            onGetWeatherData()
        } else {
            localDataSource.onGetWeatherDataByCountryId(countryId)
        }
    }

}