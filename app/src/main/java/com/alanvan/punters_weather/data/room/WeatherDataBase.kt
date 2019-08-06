package com.alanvan.punters_weather.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.alanvan.punters_weather.data.dao.CountryDao
import com.alanvan.punters_weather.data.dao.SportDao
import com.alanvan.punters_weather.data.dao.WeatherDao
import com.alanvan.punters_weather.data.model.Country
import com.alanvan.punters_weather.data.model.Sport
import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.injection.Injector

@Database(entities = [VenueWeatherData::class, Country::class, Sport::class], version = 1, exportSchema = false)
abstract class WeatherDataBase : RoomDatabase() {

    companion object {

        const val DATABASE_NAME = "punters-weather.db"

        @Volatile
        private var sInstance: WeatherDataBase? = null

        fun getDatabase(): WeatherDataBase {
            val tempInstance = sInstance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    Injector.getContextComponent().appContext(),
                    WeatherDataBase::class.java,
                    DATABASE_NAME
                ).build()
                sInstance = instance
                return instance
            }
        }
    }

    abstract fun weatherDao(): WeatherDao
    abstract fun countryDao(): CountryDao
    abstract fun sportDao(): SportDao
}