package com.alanvan.punters_weather.ui.venue

import android.support.test.runner.AndroidJUnit4
import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.data.repository.WeatherRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VenueActivityViewModelTest {

    private val mockWeatherRepository: WeatherRepository = mock()
    private val viewModel = VenueActivityViewModel()

    @Before
    fun setUp() {
        viewModel.weatherRepository = mockWeatherRepository
    }

    @Test
    fun getVenueWeatherDataById() {
        stubWeatherRepositoryOnGetWeatherDataByVenueId("id_111")
        val data = viewModel.getVenueWeatherDataById("id_111").blockingFirst()
        assertTrue(data.getVenueID() == "id_111")
    }

    private fun stubWeatherRepositoryOnGetWeatherDataByVenueId(venueId: String) {
        whenever(mockWeatherRepository.onGetWeatherDataByVenueId(venueId))
            .thenReturn(Observable.just(VenueWeatherData().apply {
                setVenueID(venueId)
            }))
    }
}