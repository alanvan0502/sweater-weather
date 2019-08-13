package com.alanvan.sweater_weather.injection.component

import com.alanvan.sweater_weather.injection.module.AppServiceModule
import com.alanvan.sweater_weather.injection.subcomponent.AppServiceComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppServiceModule::class])
interface AppComponent: AppServiceComponent