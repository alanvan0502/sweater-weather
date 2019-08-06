package com.alanvan.punters_weather.ui.main.alphabet

import android.arch.lifecycle.ViewModel
import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.injection.Injector
import io.reactivex.Observable

class AlphabeticalViewModel : ViewModel() {

    private val weatherRepository = Injector.getAppComponent().repositoryManager().getWeatherRepository()

    fun loadDataFromRepository(sortedAscending: Boolean): Observable<List<VenueWeatherData>> {
        return weatherRepository.onGetWeatherDataByAlphabet(sortedAscending)
    }
}