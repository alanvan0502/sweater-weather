package com.alanvan.punters_weather.ui.main.last_updated

import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.ui.main.MainFragmentViewModel
import io.reactivex.Observable

class LastUpdatedViewModel : MainFragmentViewModel() {

    override fun loadDataFromRepository(sortedAscending: Boolean): Observable<List<VenueWeatherData>> {
        return weatherRepository.onGetWeatherDataSortedByLastUpdated(sortedAscending)
    }
}