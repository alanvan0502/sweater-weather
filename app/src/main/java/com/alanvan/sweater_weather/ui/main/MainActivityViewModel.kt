package com.alanvan.sweater_weather.ui.main

import android.arch.lifecycle.ViewModel
import com.alanvan.sweater_weather.injection.Injector
import io.reactivex.Observable

class MainActivityViewModel: ViewModel() {

    private val weatherRepository = Injector.getAppComponent().repositoryManager().getWeatherRepository()

    // When sortedAscending == null -> unsorted
    @Volatile
    var sortedAscending: Boolean? = null

    // When countryId == null -> unfiltered
    @Volatile
    var countryId: String? = null

    fun syncWeatherData(): Observable<Boolean> {
        return weatherRepository.syncWeatherData()
    }
}