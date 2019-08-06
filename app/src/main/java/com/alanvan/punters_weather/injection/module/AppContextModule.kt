package com.alanvan.punters_weather.injection.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppContextModule(private val appContext: Context) {

    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext)

    @Provides
    @Singleton
    fun provideAppContext(): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {
        return sharedPreferences
    }
}