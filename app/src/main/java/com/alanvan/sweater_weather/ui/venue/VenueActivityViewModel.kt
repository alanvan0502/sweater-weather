package com.alanvan.sweater_weather.ui.venue

import android.arch.lifecycle.ViewModel
import com.alanvan.sweater_weather.data.model.VenueWeatherData
import com.alanvan.sweater_weather.injection.Injector
import io.reactivex.Observable

class VenueActivityViewModel : ViewModel() {

    var venueId: String? = null

    // for testing only - change to 'private val' in production code
    var weatherRepository = Injector.getAppComponent().repositoryManager().getWeatherRepository()

    fun getVenueWeatherDataById(venueId: String): Observable<VenueWeatherData> {
        return weatherRepository.onGetWeatherDataByVenueId(venueId)
    }
}