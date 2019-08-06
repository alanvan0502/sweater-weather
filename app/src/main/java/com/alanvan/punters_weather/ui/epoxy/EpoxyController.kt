package com.alanvan.punters_weather.ui.epoxy

import com.airbnb.epoxy.AsyncEpoxyController
import com.alanvan.punters_weather.data.model.VenueWeatherData

class EpoxyController : AsyncEpoxyController() {

    private var dataList: List<VenueWeatherData> = ArrayList()

    override fun buildModels() {
        dataList.forEach {
            VenueWeatherEpoxyModel_()
                    .id(it.getVenueID())
                    .venueTemperature(it.getWeatherTemp())
                    .venueCondition(it.getWeatherCondition())
                    .venueName(it.getName()).addTo(this)
        }
    }

    fun setData(data: List<VenueWeatherData>) {
        this.dataList = data
    }
}