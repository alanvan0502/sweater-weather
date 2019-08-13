package com.alanvan.sweater_weather.ui.main.alphabet

import com.alanvan.sweater_weather.data.model.VenueWeatherData
import com.alanvan.sweater_weather.ui.main.MainFragmentViewModel
import io.reactivex.Observable

class AlphabeticalViewModel : MainFragmentViewModel() {

    override fun loadDataFromRepository(sortedAscending: Boolean, countryId: String?): Observable<List<VenueWeatherData>> {
        return weatherRepository.onGetWeatherDataSortedByAlphabet(sortedAscending, countryId)
    }
}