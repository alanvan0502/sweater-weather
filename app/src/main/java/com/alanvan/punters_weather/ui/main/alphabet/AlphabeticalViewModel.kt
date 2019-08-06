package com.alanvan.punters_weather.ui.main.alphabet

import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.ui.main.MainFragmentViewModel
import io.reactivex.Observable

class AlphabeticalViewModel : MainFragmentViewModel() {

    override fun loadDataFromRepository(sortedAscending: Boolean): Observable<List<VenueWeatherData>> {
        return weatherRepository.onGetWeatherDataByAlphabet(sortedAscending)
    }
}