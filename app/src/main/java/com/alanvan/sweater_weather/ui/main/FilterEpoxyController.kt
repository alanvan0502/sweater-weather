package com.alanvan.sweater_weather.ui.main

import android.view.View
import com.airbnb.epoxy.AsyncEpoxyController
import com.alanvan.sweater_weather.R
import com.alanvan.sweater_weather.data.model.Country
import com.alanvan.sweater_weather.injection.Injector
import com.alanvan.sweater_weather.ui.epoxy.CountryFilterEpoxyModel_

class FilterEpoxyController(private val owner: Callback) : AsyncEpoxyController() {

    private var dataList: List<Country> = ArrayList()
    private val appContext = Injector.getContextComponent().appContext()

    override fun buildModels() {
        dataList.forEach {

            val countryName = if (it.getName() != null) {
                it.getName()
            } else {
                appContext.getString(R.string.venue_name)
            }

            val countryID = it.getCountryID()

            CountryFilterEpoxyModel_()
                .id(it.getCountryID())
                .countryName(countryName)
                .onClickListener(View.OnClickListener {
                    owner.onItemClick(countryID)
                }).addTo(this)
        }
    }

    fun setData(data: List<Country>) {
        this.dataList = data
    }

    interface Callback {
        fun onItemClick(countryID: String?)
    }
}