package com.alanvan.sweater_weather.data.repository

import com.alanvan.sweater_weather.data.model.Country
import com.alanvan.sweater_weather.data.model.VenueWeatherData
import io.reactivex.Observable

interface WeatherRepository {

    fun syncWeatherData(): Observable<Boolean>

    fun onGetWeatherDataSortedByAlphabet(sortedAscending: Boolean, countryId: String?): Observable<List<VenueWeatherData>>

    fun onGetWeatherDataSortedByTemperature(sortedAscending: Boolean, countryId: String?): Observable<List<VenueWeatherData>>

    fun onGetWeatherDataSortedByLastUpdated(sortedAscending: Boolean, countryId: String?): Observable<List<VenueWeatherData>>

    fun onGetWeatherData(): Observable<List<VenueWeatherData>>

    fun onGetAllCountries(): Observable<List<Country>>

    fun onGetWeatherDataByCountryId(countryId: String?): Observable<List<VenueWeatherData>>

    fun onGetWeatherDataByVenueId(venueId: String): Observable<VenueWeatherData>
}