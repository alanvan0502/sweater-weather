package com.alanvan.punters_weather.ui.main

import android.arch.lifecycle.ViewModel
import com.alanvan.punters_weather.injection.Injector
import io.reactivex.Observable

class MainActivityViewModel: ViewModel() {

    private val weatherRepository = Injector.getAppComponent().repositoryManager().getWeatherRepository()

    fun syncWeatherData(): Observable<Boolean> {
        return weatherRepository.syncWeatherData()
    }
}