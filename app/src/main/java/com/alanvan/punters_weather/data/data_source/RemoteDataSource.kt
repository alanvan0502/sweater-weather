package com.alanvan.punters_weather.data.data_source

import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.injection.Injector
import io.reactivex.Observable

class RemoteDataSource {

    companion object {

        @Volatile
        private var sInstance: RemoteDataSource? = null

        private val LOCK = Object()

        fun getInstance(): RemoteDataSource {
            return synchronized(LOCK) {
                if (sInstance == null) {
                    sInstance = RemoteDataSource()
                }
                return@synchronized sInstance!!
            }
        }
    }

    fun syncWeatherData(): Observable<List<VenueWeatherData>> {
        return Injector.getAppComponent().weatherEndPoint().loadData()
            .map { it.getData() }.map {
                it.forEach { venueData ->
                    venueData.apply {
                        getSport()?.getSportID()?.let { id ->
                            setSportID(id)
                        }
                        getCountry()?.getCountryID()?.let { id ->
                            setCountryID(id)
                        }
                    }
                }
                return@map it
            }
    }
}