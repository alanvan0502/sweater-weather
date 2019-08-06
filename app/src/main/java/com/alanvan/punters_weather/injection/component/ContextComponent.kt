package com.alanvan.punters_weather.injection.component

import android.content.Context
import android.content.SharedPreferences
import com.alanvan.punters_weather.injection.module.AppContextModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppContextModule::class])
interface ContextComponent {

    fun appContext(): Context

    fun sharedPreferences(): SharedPreferences
}