package com.alanvan.sweater_weather.data.data_source

import com.alanvan.sweater_weather.data.model.VenueWeatherData
import com.alanvan.sweater_weather.injection.Injector
import io.reactivex.Observable

// open for testing only, for production code, remove 'open' access modifier
open class RemoteDataSource {

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