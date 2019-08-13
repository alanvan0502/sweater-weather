package com.alanvan.sweater_weather.ui.main.last_updated

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.alanvan.sweater_weather.ui.main.MainFragment

class LastUpdatedFragment : MainFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LastUpdatedViewModel::class.java)
    }

}