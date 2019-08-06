package com.alanvan.punters_weather.data.dao

import android.arch.persistence.room.Dao
import com.alanvan.punters_weather.data.model.Country

@Dao
abstract class CountryDao : BaseDao<Country>() {

}