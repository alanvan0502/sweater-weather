package com.alanvan.punters_weather.ui.venue

import android.arch.lifecycle.ViewModel
import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.injection.Injector
import io.reactivex.Observable

class VenueActivityViewModel : ViewModel() {

    var venueId: String? = null

    private val weatherRepository = Injector.getAppComponent().repositoryManager().getWeatherRepository()

    fun getVenueWeatherDataById(venueId: String): Observable<VenueWeatherData> {
        return weatherRepository.onGetWeatherDataByVenueId(venueId)
    }
}