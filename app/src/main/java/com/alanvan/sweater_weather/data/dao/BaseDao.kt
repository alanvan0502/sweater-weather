package com.alanvan.sweater_weather.data.dao

import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy

abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(obj: List<T>)

}