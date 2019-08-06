package com.alanvan.punters_weather

import android.app.Application
import com.alanvan.punters_weather.injection.Injector
import com.alanvan.punters_weather.injection.module.AppServiceModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Application initiations
        Injector.initApp(this)
        Injector.initModules(AppServiceModule())
    }
}