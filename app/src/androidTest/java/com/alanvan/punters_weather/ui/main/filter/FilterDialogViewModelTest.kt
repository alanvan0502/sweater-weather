package com.alanvan.punters_weather.ui.main.filter

import com.alanvan.punters_weather.data.model.Country
import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.data.repository.WeatherRepository
import com.alanvan.punters_weather.ui.main.alphabet.AlphabeticalViewModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before

import org.junit.Assert.*
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