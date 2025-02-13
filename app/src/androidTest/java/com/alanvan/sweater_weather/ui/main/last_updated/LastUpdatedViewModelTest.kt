package com.alanvan.sweater_weather.ui.main.last_updated

import android.support.test.runner.AndroidJUnit4
import com.alanvan.sweater_weather.data.model.VenueWeatherData
import com.alanvan.sweater_weather.data.repository.WeatherRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LastUpdatedViewModelTest {

    private val mockWeatherRepository: WeatherRepository = mock()
    private val viewModel = LastUpdatedViewModel()

    @Before
    fun setUp() {
        viewModel.weatherRepository = mockWeatherRepository
    }

    @Test
    fun loadDataFromRepository() {
        stubOnGetWeatherDataSortedByLastUpdated(true, "1")
        val result = viewModel.loadDataFromRepository(true, "1").blockingFirst()
        assert(result.size == 100)
        assert(result[0].getWeatherTemp() == 0)
        assert(result[99].getWeatherTemp() == 100)
    }


    private fun stubOnGetWeatherDataSortedByLastUpdated(sortedAscending: Boolean, countryId: String?) {
        val mockData = ArrayList<VenueWeatherData>()
        for (i in 0 until 100) {
            mockData.add(VenueWeatherData().apply {
                setWeatherLastUpdated(i)
                setCountryID("1")
            })
        }
        whenever(mockWeatherRepository.onGetWeatherDataSortedByLastUpdated(sortedAscending, countryId))
            .thenReturn(Observable.just(mockData))
    }
}