package com.alanvan.punters_weather.ui.main.last_updated

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyRecyclerView
import com.alanvan.punters_weather.R
import com.alanvan.punters_weather.RxFragment
import com.alanvan.punters_weather.ui.main.MainFragment
import com.alanvan.punters_weather.ui.main.temperature.TemperatureViewModel

class LastUpdatedFragment : MainFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LastUpdatedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_last_updated, container, false)
        val recyclerView: EpoxyRecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setController(epoxyController)

        return view
    }
}