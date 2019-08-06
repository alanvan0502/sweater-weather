package com.alanvan.punters_weather.ui.main.temperature

import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.ui.main.MainViewModel
import io.reactivex.Observable

class TemperatureViewModel : MainViewModel() {

    override fun loadDataFromRepository(sortedAscending: Boolean): Observable<List<VenueWeatherData>> {
        return weatherRepository.onGetWeatherDataByTemperature(sortedAscending)
    }
}