package com.alanvan.sweater_weather.ui.main.temperature

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.alanvan.sweater_weather.ui.main.MainFragment

class TemperatureFragment : MainFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TemperatureViewModel::class.java)
    }

}