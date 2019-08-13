package com.alanvan.sweater_weather.injection.subcomponent

import com.alanvan.sweater_weather.network.endpoints.WeatherEndPoint
import com.alanvan.sweater_weather.data.repository.RepositoryManager

interface AppServiceComponent {

    fun repositoryManager(): RepositoryManager

    fun weatherEndPoint(): WeatherEndPoint

}