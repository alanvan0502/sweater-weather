package com.alanvan.punters_weather.injection.component

import com.alanvan.punters_weather.injection.module.AppServiceModule
import com.alanvan.punters_weather.injection.subcomponent.AppServiceComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppServiceModule::class])
interface AppComponent: AppServiceComponent