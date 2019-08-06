package com.alanvan.punters_weather.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alanvan.punters_weather.R
import com.alanvan.punters_weather.injection.Injector
import com.alanvan.punters_weather.utils.RxUtils

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
