package com.alanvan.punters_weather.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.alanvan.punters_weather.ui.main.alphabet.AlphabeticalFragment
import com.alanvan.punters_weather.ui.main.last_updated.LastUpdatedFragment
import com.alanvan.punters_weather.ui.main.temperature.TemperatureFragment

class PagerAdapter(fragmentManager: FragmentManager, private val numOfTabs: Int) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> AlphabeticalFragment()
            1 -> TemperatureFragment()
            2 -> LastUpdatedFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return numOfTabs
    }

}