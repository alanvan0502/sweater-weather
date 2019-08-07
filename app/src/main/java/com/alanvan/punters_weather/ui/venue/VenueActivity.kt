package com.alanvan.punters_weather.ui.venue

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import com.alanvan.punters_weather.R
import com.alanvan.punters_weather.data.model.VenueWeatherData
import com.alanvan.punters_weather.utils.RxUtils
import java.text.SimpleDateFormat
import java.util.*

class VenueActivity : AppCompatActivity() {

    companion object {
        const val VENUE_ID_EXTRA = "venue-id-extra"
    }

    private lateinit var toolbar: Toolbar
    private var viewModel: VenueActivityViewModel? = null
    private lateinit var feelsLikeTv: TextView
    private lateinit var humidityTv: TextView
    private lateinit var windTv: TextView
    private lateinit var tempTv: TextView
    private lateinit var nameTv: TextView
    private lateinit var conditionTv: TextView
    private lateinit var lastUpdatedTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venue)

        // setup toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        toolbar.apply {
            setNavigationIcon(R.drawable.ic_keyboard_arrow_left_48dp)
            setNavigationOnClickListener {
                finish()
            }
        }

        // get venue id
        val intent = intent
        val venueId = intent.getStringExtra(VENUE_ID_EXTRA)

        viewModel = ViewModelProviders.of(this).get(VenueActivityViewModel::class.java)
        viewModel?.venueId = venueId

        nameTv = findViewById(R.id.venue_name)
        tempTv = findViewById(R.id.venue_temperature)
        feelsLikeTv = findViewById(R.id.venue_feels_like_data)
        humidityTv = findViewById(R.id.venue_humidity_data)
        windTv = findViewById(R.id.venue_wind_data)
        conditionTv = findViewById(R.id.venue_condition)
        lastUpdatedTv = findViewById(R.id.venue_last_updated)

        viewModel?.let { vm ->
            vm.venueId?.let {
                vm.getVenueWeatherDataById(it).compose(RxUtils.applyIOSchedulers())
                    .subscribe({ data ->
                        // stop loading
                        populateData(data)
                    }, {

                    })
            }
        }
    }

    private fun populateData(data: VenueWeatherData) {

        data.getName()?.let {
            nameTv.text = it
        }

        data.getWeatherCondition()?.let {
            conditionTv.text = it
        }

        val lastUpdated = if (data.getWeatherLastUpdated() == null) {
            getString(R.string.venue_updated_at_no_data)
        } else {
            var dateString: String? = null
            try {
                val lastUpdated = data.getWeatherLastUpdated()?.toLong()
                var date: Date? = null
                lastUpdated?.let {
                    date = Date(it * 1000)
                }
                date?.let {
                    val pattern = "HH:mma, EEEE d MMMM yyyy "
                    dateString = getString(R.string.venue_updated_at) + " " + SimpleDateFormat(pattern, Locale.US).format(it)
                }
            } catch (e: Throwable) {
                // do nothing
            }

            if (dateString == null) {
                getString(R.string.venue_updated_at_no_data)
            } else {
                dateString
            }
        }
        lastUpdatedTv.text = lastUpdated

        val temp = if (data.getWeatherTemp() == null) {
            getString(R.string.venue_feels_like_no_data)
        } else {
            data.getWeatherTemp().toString() + "\u2103"
        }
        tempTv.text = temp

        val feelsLike = if (data.getWeatherFeelsLike() == null) {
            getString(R.string.venue_feels_like_no_data)
        } else {
            data.getWeatherFeelsLike() + "\u2103"
        }
        feelsLikeTv.text = feelsLike

        val humidity = if (data.getWeatherHumidity() == null) {
            getString(R.string.venue_humidity_no_data)
        } else {
            data.getWeatherHumidity() + "%"
        }
        humidityTv.text = humidity

        val wind = if (data.getWeatherWind() == null) {
            getString(R.string.venue_wind_no_data)
        } else {
            data.getWeatherWind()
        }
        windTv.text = wind
    }
}
