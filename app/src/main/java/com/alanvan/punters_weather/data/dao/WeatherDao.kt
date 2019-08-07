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
}