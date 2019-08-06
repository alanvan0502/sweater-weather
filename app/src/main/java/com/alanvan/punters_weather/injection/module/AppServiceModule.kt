package com.alanvan.punters_weather.injection.module

import com.alanvan.punters_weather.network.endpoints.WeatherEndPoint
import com.alanvan.punters_weather.network.service.NetworkServiceFactory
import com.alanvan.punters_weather.repository.RepositoryManager
import com.alanvan.punters_weather.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppServiceModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(): WeatherRepository {
        return RepositoryManager.getWeatherRepository()
    }

    @Provides
    @Singleton
    fun provideNetworkService(): WeatherEndPoint {
        return NetworkServiceFactory.createService(WeatherEndPoint::class.java, WeatherEndPoint.END_POINT)
    }

    @Provides
    @Singleton
    fun provideRepositoryManager(): RepositoryManager {
        return RepositoryManager
    }
}