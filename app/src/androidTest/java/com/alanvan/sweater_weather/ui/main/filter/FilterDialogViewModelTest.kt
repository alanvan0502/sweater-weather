package com.alanvan.sweater_weather.ui.main.filter

import com.alanvan.sweater_weather.data.model.Country
import com.alanvan.sweater_weather.data.repository.WeatherRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before

import org.junit.Test

class FilterDialogViewModelTest {

    private val mockWeatherRepository: WeatherRepository = mock()
    private val viewModel = FilterDialogViewModel()

    @Before
    fun setUp() {
        viewModel.weatherRepository = mockWeatherRepository
    }

    @Test
    fun loadDataFromRepository() {
        stubOnGetAllCountries()
        val result = viewModel.loadCountries().blockingFirst()
        assert(result.size == 100)
    }

    private fun stubOnGetAllCountries() {
        val mockData = ArrayList<Country>()
        for (i in 0 until 100) {
            mockData.add(Country().apply {
                setName("1")
                setCountryID("1")
            })
        }
        whenever(mockWeatherRepository.onGetAllCountries())
            .thenReturn(Observable.just(mockData))
    }
}