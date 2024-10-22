package com.alanvan.sweater_weather.injection

import android.app.Application
import com.alanvan.sweater_weather.injection.component.AppComponent
import com.alanvan.sweater_weather.injection.component.ContextComponent
import com.alanvan.sweater_weather.injection.component.DaggerAppComponent
import com.alanvan.sweater_weather.injection.component.DaggerContextComponent
import com.alanvan.sweater_weather.injection.module.AppContextModule
import com.alanvan.sweater_weather.injection.module.AppServiceModule

class Injector(application: Application) {

    private lateinit var appComponent: AppComponent
    private val contextComponent: ContextComponent = DaggerContextComponent.builder()
        .appContextModule(AppContextModule(application.applicationContext))
        .build()

    companion object {

        private lateinit var sInstance: Injector

        fun initApp(application: Application) {
            sInstance = Injector(application)
        }

        fun initModules(appServiceModule: AppServiceModule) {
            sInstance.appComponent = DaggerAppComponent.builder().appServiceModule(appServiceModule).build()
        }

        fun getContextComponent(): ContextComponent {
            return sInstance.contextComponent
        }

        fun getAppComponent(): AppComponent {
            return sInstance.appComponent
        }

    }


}
