package com.alanvan.sweater_weather.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.alanvan.sweater_weather.data.model.Sport

@Dao
abstract class SportDao : BaseDao<Sport>() {

    /**
     * For test insert
     */
    @Query("SELECT * FROM Sport")
    abstract fun getAllSports(): List<Sport>

}