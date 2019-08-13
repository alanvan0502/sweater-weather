package com.alanvan.sweater_weather.ui.main

import android.view.View
import com.airbnb.epoxy.AsyncEpoxyController
import com.alanvan.sweater_weather.R
import com.alanvan.sweater_weather.data.model.VenueWeatherData
import com.alanvan.sweater_weather.injection.Injector
import com.alanvan.sweater_weather.ui.epoxy.VenueWeatherEpoxyModel_

class MainEpoxyController(private val owner: Callback) : AsyncEpoxyController() {

    private var dataList: List<VenueWeatherData> = ArrayList()
    private val appContext = Injector.getContextComponent().appContext()

    override fun buildModels() {
        dataList.forEach { data ->
            val temperature = if (data.getWeatherTemp() != null) {
                "${data.getWeatherTemp()} \u2103"
            } else {
                "- \u2103"
            }
            VenueWeatherEpoxyModel_()
                .id(data.getVenueID())
                .venueTemperature(temperature)
                .venueCondition(data.getWeatherCondition() ?: appContext.getString(R.string.venue_condition_unknown))
                .venueName(data.getName() ?: appContext.getString(R.string.venue_name))
                .onClickListener(View.OnClickListener {
                    owner.onItemClicked(data.getVenueID())
                }).addTo(this)
        }
    }

    fun setData(data: List<VenueWeatherData>) {
        this.dataList = data
    }

    interface Callback {
        fun onItemClicked(venueId: String)
    }
}