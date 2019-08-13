package com.alanvan.sweater_weather.ui.main

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout

class TabLayoutPageChangeListener(tabLayout: TabLayout, private val swipeRefreshLayout: SwipeRefreshLayout)
    : TabLayout.TabLayoutOnPageChangeListener(tabLayout) {

    override fun onPageScrollStateChanged(state: Int) {
        super.onPageScrollStateChanged(state)
        swipeRefreshLayout.isEnabled = state == ViewPager.SCROLL_STATE_IDLE
    }
}