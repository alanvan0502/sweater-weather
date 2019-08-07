package com.alanvan.punters_weather.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.alanvan.punters_weather.data.model.VenueWeatherData

@Dao
abstract class WeatherDao : BaseDao<VenueWeatherData>() {

    @Query("SELECT * FROM VenueWeatherData ORDER BY CASE WHEN :sortedAscending = 1 THEN _name END ASC, CASE WHEN :sortedAscending = 0 THEN _name END DESC")
    abstract fun getWeatherDataSortedByAlphabet(sortedAscending: Boolean): List<VenueWeatherData>

    @Query("SELECT * FROM VenueWeatherData ORDER BY CASE WHEN :sortedAscending = 1 THEN _weatherTemp END ASC, CASE WHEN :sortedAscending = 0 THEN _weatherTemp END DESC")
    abstract fun getWeatherDataSortedByTemperature(sortedAscending: Boolean): List<VenueWeatherData>

    @Query("SELECT * FROM VenueWeatherData ORDER BY CASE WHEN :sortedAscending = 1 THEN _weatherLastUpdated END ASC, CASE WHEN :sortedAscending = 0 THEN _weatherLastUpdated END DESC")
    abstract fun getWeatherDataSortedByByLastUpdated(sortedAscending: Boolean): List<VenueWeatherData>

    @Query("SELECT * FROM VenueWeatherData WHERE _countryID IS :countryId ORDER BY CASE WHEN :sortedAscending = 1 THEN _name END ASC, CASE WHEN :sortedAscending = 0 THEN _name END DESC")
    abstract fun getWeatherDataSortedByAlphabetFiltered(sortedAscending: Boolean, countryId: String): List<VenueWeatherData>

    @Query("SELECT * FROM VenueWeatherData WHERE _countryID IS :countryId ORDER BY CASE WHEN :sortedAscending = 1 THEN _weatherTemp END ASC, CASE WHEN :sortedAscending = 0 THEN _weatherTemp END DESC")
    abstract fun getWeatherDataSortedByTemperatureFiltered(sortedAscending: Boolean, countryId: String): List<VenueWeatherData>

    @Query("SELECT * FROM VenueWeatherData WHERE _countryID IS :countryId ORDER BY CASE WHEN :sortedAscending = 1 THEN _weatherLastUpdated END ASC, CASE WHEN :sortedAscending = 0 THEN _weatherLastUpdated END DESC")
    abstract fun getWeatherDataSortedByByLastUpdatedFiltered(sortedAscending: Boolean, countryId: String): List<VenueWeatherData>

    @Query("SELECT * FROM VenueWeatherData WHERE _countryID IS :countryId")
    abstract fun getWeatherDataByCountryId(countryId: String): List<VenueWeatherData>

    @Query("SELECT * FROM VenueWeatherData")
    abstract fun getWeatherData(): List<VenueWeatherData>

    @Query("SELECT * FROM VenueWeatherData WHERE _venueID IS :venueId")
    abstract fun getWeatherDataByVenueId(venueId: String): VenueWeatherData
}