package com.alanvan.punters_weather.ui.main.alphabet

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.alanvan.punters_weather.ui.main.MainFragment

class AlphabeticalFragment : MainFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AlphabeticalViewModel::class.java)
    }

}