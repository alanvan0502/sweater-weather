package com.alanvan.punters_weather.ui.venue

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.alanvan.punters_weather.R

class VenueActivity : AppCompatActivity() {

    companion object {
        const val VENUE_ID_EXTRA = "venue-id-extra"
    }

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venue)

        // setup toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
    }
}
