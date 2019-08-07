package com.alanvan.punters_weather.ui.main.alphabet

import android.support.test.runner.AndroidJUnit4
import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.data.repository.WeatherRepository
import com.alanvan.punters_weather.ui.main.last_updated.LastUpdatedViewModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlphabeticalViewModelTest {

    private val mockWeatherRepository: WeatherRepository = mock()
    private val viewModel = AlphabeticalViewModel()

    @Before
    fun setUp() {
        viewModel.weatherRepository = mockWeatherRepository
    }

    @Test
    fun loadDataFromRepository() {
        stubOnGetWeatherDataSortedByAlphabet(true, "1")
        val result = viewModel.loadDataFromRepository(true, "1").blockingFirst()
        assert(result.size == 100)
        assert(result[0].getWeatherTemp() == 0)
        assert(result[99].getWeatherTemp() == 100)
    }


    private fun stubOnGetWeatherDataSortedByAlphabet(sortedAscending: Boolean, countryId: String?) {
        val mockData = ArrayList<VenueWeatherData>()
        for (i in 0 until 100) {
            mockData.add(VenueWeatherData().apply {
                setName("1")
                setCountryID("1")
            })
        }
        whenever(mockWeatherRepository.onGetWeatherDataSortedByAlphabet(sortedAscending, countryId))
            .thenReturn(Observable.just(mockData))
    }
}