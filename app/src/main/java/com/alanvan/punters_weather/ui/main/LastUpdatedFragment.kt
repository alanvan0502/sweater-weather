package com.alanvan.punters_weather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alanvan.punters_weather.R
import com.alanvan.punters_weather.RxFragment

class LastUpdatedFragment : RxFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_last_updated, container, false)
    }
}