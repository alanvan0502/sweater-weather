package com.alanvan.sweater_weather.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.alanvan.sweater_weather.data.model.Country

@Dao
abstract class CountryDao : BaseDao<Country>() {

    @Query("SELECT * FROM country")
    abstract fun getAllCountries(): List<Country>
}