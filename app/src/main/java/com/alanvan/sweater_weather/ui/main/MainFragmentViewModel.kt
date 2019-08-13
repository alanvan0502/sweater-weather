package com.alanvan.sweater_weather.ui.main

import android.arch.lifecycle.ViewModel
import com.alanvan.sweater_weather.data.model.VenueWeatherData
import com.alanvan.sweater_weather.injection.Injector
import io.reactivex.Observable

abstract class MainFragmentViewModel: ViewModel() {

    // for testing only - change to 'protected val' in production code
    var weatherRepository = Injector.getAppComponent().repositoryManager().getWeatherRepository()

    abstract fun loadDataFromRepository(sortedAscending: Boolean, countryId: String?): Observable<List<VenueWeatherData>>

    fun loadDataFromRepositoryByCountryId(countryId: String?): Observable<List<VenueWeatherData>> {
        return weatherRepository.onGetWeatherDataByCountryId(countryId)
    }
}