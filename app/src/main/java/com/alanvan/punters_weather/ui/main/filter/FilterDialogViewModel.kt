package com.alanvan.punters_weather.ui.main.filter

import android.arch.lifecycle.ViewModel
import com.alanvan.punters_weather.data.model.Country
import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.injection.Injector
import com.alanvan.punters_weather.ui.main.MainFragmentViewModel
import io.reactivex.Observable

class FilterDialogViewModel : ViewModel() {

    // for testing only - change to 'private val' in production code
    var weatherRepository = Injector.getAppComponent().repositoryManager().getWeatherRepository()

    fun loadCountries(): Observable<List<Country>> {
        return weatherRepository.onGetAllCountries()
    }
}