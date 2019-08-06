package com.alanvan.punters_weather.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.alanvan.punters_weather.R

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: PagerAdapter

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        tabLayout = findViewById<TabLayout>(R.id.tab_layout).apply {
            addTab(this.newTab().setText(context.getString(R.string.alphabetical_title)))
            addTab(this.newTab().setText(context.getString(R.string.temperature_title)))
            addTab(this.newTab().setText(context.getString(R.string.last_updated_title)))
            tabGravity = TabLayout.GRAVITY_FILL
        }

        viewPager = findViewById(R.id.pager)
        pagerAdapter = PagerAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = pagerAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabReselected(tab: TabLayout.Tab) {
                // not implemented
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // not implemented
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

        })
    }
}
