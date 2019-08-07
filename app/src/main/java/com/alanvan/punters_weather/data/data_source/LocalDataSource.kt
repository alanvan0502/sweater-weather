package com.alanvan.punters_weather.data.data_source

import com.alanvan.punters_weather.data.model.Country
import com.alanvan.punters_weather.data.model.Sport
import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.data.room.WeatherDataBase
import io.reactivex.Observable

class LocalDataSource {

    private val weatherDao = WeatherDataBase.getDatabase().weatherDao()
    private val countryDao = WeatherDataBase.getDatabase().countryDao()
    private val sportDao = WeatherDataBase.getDatabase().sportDao()

    companion object {
        @Volatile
        private var sInstance: LocalDataSource? = null

        private val LOCK = Object()

        fun getInstance(): LocalDataSource {
            return synchronized(LOCK) {
                if (sInstance == null) {
                    sInstance = LocalDataSource()
                }
                return@synchronized sInstance!!
            }
        }
    }

    fun saveWeatherData(listData: List<VenueWeatherData>) {
        weatherDao.insert(listData)

        val sportList = ArrayList<Sport>()
        val countryList = ArrayList<Country>()

        listData.forEach {

            it.getSport()?.let { sport ->
                if (!sportList.contains(sport)) {
                    sportList.add(sport)
                }
            }

            it.getCountry()?.let { country ->
                if (!countryList.contains(country)) {
                    countryList.add(country)
                }
            }

            sportDao.insert(sportList)
            countryDao.insert(countryList)
        }
    }

    fun onGetWeatherDataSortedByAlphabet(sortedAscending: Boolean): Observable<List<VenueWeatherData>> {
        return Observable.fromCallable { weatherDao.getWeatherDataSortedByAlphabet(sortedAscending) }
    }

    fun onGetWeatherDataSortedByTemperature(sortedAscending: Boolean): Observable<List<VenueWeatherData>> {
        return Observable.fromCallable { weatherDao.getWeatherDataSortedByTemperature(sortedAscending) }
    }

    fun onGetWeatherDataSortedByLastUpdated(sortedAscending: Boolean): Observable<List<VenueWeatherData>> {
        return Observable.fromCallable { weatherDao.getWeatherDataSortedByByLastUpdated(sortedAscending) }
    }

    fun getVenueWeather(venueId: Int): Observable<VenueWeatherData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onGetAllCountries(): Observable<List<Country>> {
        return Observable.fromCallable { countryDao.getAllCountries() }
    }
}