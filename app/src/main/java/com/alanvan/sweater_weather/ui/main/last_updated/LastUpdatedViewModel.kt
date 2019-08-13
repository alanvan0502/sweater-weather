package com.alanvan.sweater_weather.ui.main.last_updated

import com.alanvan.sweater_weather.data.model.VenueWeatherData
import com.alanvan.sweater_weather.ui.main.MainFragmentViewModel
import io.reactivex.Observable

class LastUpdatedViewModel : MainFragmentViewModel() {

    override fun loadDataFromRepository(sortedAscending: Boolean, countryId: String?): Observable<List<VenueWeatherData>> {
        return weatherRepository.onGetWeatherDataSortedByLastUpdated(sortedAscending, countryId)
    }
}