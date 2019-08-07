package com.alanvan.punters_weather.ui.main

import android.arch.lifecycle.ViewModel
import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.injection.Injector
import io.reactivex.Observable

abstract class MainFragmentViewModel: ViewModel() {

    protected val weatherRepository = Injector.getAppComponent().repositoryManager().getWeatherRepository()

    abstract fun loadDataFromRepository(sortedAscending: Boolean, countryId: String?): Observable<List<VenueWeatherData>>

    fun loadDataFromRepositoryByCountryId(countryId: String?): Observable<List<VenueWeatherData>> {
        return weatherRepository.onGetWeatherDataByCountryId(countryId)
    }
}