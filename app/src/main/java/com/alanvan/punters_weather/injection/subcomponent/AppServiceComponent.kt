package com.alanvan.punters_weather.injection.subcomponent

import com.alanvan.punters_weather.network.endpoints.WeatherEndPoint
import com.alanvan.punters_weather.data.repository.RepositoryManager

interface AppServiceComponent {

    fun repositoryManager(): RepositoryManager

    fun weatherEndPoint(): WeatherEndPoint

}