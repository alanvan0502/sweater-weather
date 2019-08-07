package com.alanvan.punters_weather.data.data_source

import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RemoteDataSourceTest {

    private val remoteDataSource: RemoteDataSource = mock()

    @Test
    fun syncWeatherData() {
        val cloudData = remoteDataSource.syncWeatherData().blockingFirst()
        Log.d("RemoteDataSourceTest", "totalData ${cloudData.size}")
    }
}