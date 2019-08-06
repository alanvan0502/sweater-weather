package com.alanvan.punters_weather.data.data_source

import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.injection.Injector
import io.reactivex.Observable

class RemoteDataSource : DataSource {

    companion object {
        private var sInstance: DataSource? = null
        private val LOCK = Object()

        fun getInstance(): DataSource {
            return synchronized(LOCK) {
                if (sInstance == null) {
                    sInstance = RemoteDataSource()
                }
                return@synchronized sInstance!!
            }
        }
    }

    override fun getVenueWeatherData(): Observable<List<VenueWeatherData>> {
        return Injector.getAppComponent().weatherEndPoint().loadData().map { it.getData() }
    }

    override fun getVenueWeather(venueId: Int): Observable<VenueWeatherData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}