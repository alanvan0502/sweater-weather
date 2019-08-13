package com.alanvan.sweater_weather.ui.main.filter

import android.arch.lifecycle.ViewModel
import com.alanvan.sweater_weather.data.model.Country
import com.alanvan.sweater_weather.injection.Injector
import io.reactivex.Observable

class FilterDialogViewModel : ViewModel() {

    // for testing only - change to 'private val' in production code
    var weatherRepository = Injector.getAppComponent().repositoryManager().getWeatherRepository()

    fun loadCountries(): Observable<List<Country>> {
        return weatherRepository.onGetAllCountries()
    }
}