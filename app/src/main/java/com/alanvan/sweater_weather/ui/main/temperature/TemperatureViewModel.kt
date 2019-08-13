package com.alanvan.sweater_weather.ui.main.temperature

import com.alanvan.sweater_weather.data.model.VenueWeatherData
import com.alanvan.sweater_weather.ui.main.MainFragmentViewModel
import io.reactivex.Observable

class TemperatureViewModel : MainFragmentViewModel() {

    override fun loadDataFromRepository(sortedAscending: Boolean, countryId: String?): Observable<List<VenueWeatherData>> {
        return weatherRepository.onGetWeatherDataSortedByTemperature(sortedAscending, countryId)
    }
}