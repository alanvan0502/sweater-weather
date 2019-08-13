package com.alanvan.sweater_weather.ui.main.temperature

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
class TemperatureViewModelTest {

    private val mockWeatherRepository: WeatherRepository = mock()
    private val viewModel = TemperatureViewModel()

    @Before
    fun setUp() {
        viewModel.weatherRepository = mockWeatherRepository
    }

    @Test
    fun loadDataFromRepository() {
        stubOnGetWeatherDataSortedByTemperature(true, "1")
        val result = viewModel.loadDataFromRepository(true, "1").blockingFirst()
        assert(result.size == 100)
        assert(result[0].getWeatherTemp() == 0)
        assert(result[99].getWeatherTemp() == 100)
    }

    @Test
    fun loadDataFromRepositoryByCountryId() {
        stubOnGetWeatherDataByCountryId("1")
        val result = viewModel.loadDataFromRepositoryByCountryId("1").blockingFirst()
        assert(result.size == 50)
    }

    private fun stubOnGetWeatherDataSortedByTemperature(sortedAscending: Boolean, countryId: String?) {
        val mockData = ArrayList<VenueWeatherData>()
        for (i in 0 until 100) {
            mockData.add(VenueWeatherData().apply {
                setWeatherTemp(i)
                setCountryID("1")
            })
        }
        whenever(mockWeatherRepository.onGetWeatherDataSortedByTemperature(sortedAscending, countryId))
            .thenReturn(Observable.just(mockData))
    }

    private fun stubOnGetWeatherDataByCountryId(countryId: String?) {
        val mockData = ArrayList<VenueWeatherData>()
        for (i in 0 until 50) {
            mockData.add(VenueWeatherData().apply {
                setCountryID("1")
            })
        }
        whenever(mockWeatherRepository.onGetWeatherDataByCountryId(countryId))
            .thenReturn(Observable.just(mockData))
    }
}