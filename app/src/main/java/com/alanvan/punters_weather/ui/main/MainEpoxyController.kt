package com.alanvan.punters_weather.ui.main

import com.airbnb.epoxy.AsyncEpoxyController
import com.alanvan.punters_weather.R
import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.injection.Injector
import com.alanvan.punters_weather.ui.epoxy.VenueWeatherEpoxyModel_

class MainEpoxyController : AsyncEpoxyController() {

    private var dataList: List<VenueWeatherData> = ArrayList()
    private val appContext = Injector.getContextComponent().appContext()

    override fun buildModels() {
        dataList.forEach {
            val temperature = if (it.getWeatherTemp() != null) {
                "${it.getWeatherTemp()} \u2103"
            } else {
                "- \u2103"
            }
            VenueWeatherEpoxyModel_()
                .id(it.getVenueID())
                .venueTemperature(temperature)
                .venueCondition(it.getWeatherCondition() ?: appContext.getString(R.string.venue_condition_unknown))
                .venueName(it.getName() ?: appContext.getString(R.string.venue_name)).addTo(this)
        }
    }

    fun setData(data: List<VenueWeatherData>) {
        this.dataList = data
    }
}