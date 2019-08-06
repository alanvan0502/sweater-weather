package com.alanvan.punters_weather.injection

import android.app.Application
import com.alanvan.punters_weather.injection.component.AppComponent
import com.alanvan.punters_weather.injection.component.ContextComponent
import com.alanvan.punters_weather.injection.module.AppContextModule
import com.alanvan.punters_weather.injection.module.AppServiceModule

class Injector(application: Application) {

    private var mAppComponent: AppComponent? = null
    private val mContextComponent: ContextComponent

    val instance: Injector?
        get() = sInstance

    init {
        mContextComponent = DaggerContextComponent.builder()
            .appContextModule(AppContextModule(application.applicationContext))
            .build()
    }


}
