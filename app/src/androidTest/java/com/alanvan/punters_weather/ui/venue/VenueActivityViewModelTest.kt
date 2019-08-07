package com.alanvan.punters_weather.ui.venue

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.alanvan.punters_weather.data.repository.WeatherRepositoryImpl
import com.nhaarman.mockito_kotlin.mock
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class VenueActivityViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockWeatherRepository: WeatherRepositoryImpl = mock()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getVenueWeatherDataById() {
    }
}