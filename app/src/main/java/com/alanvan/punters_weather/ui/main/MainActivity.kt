package com.alanvan.punters_weather.ui.main

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.alanvan.punters_weather.R
import com.alanvan.punters_weather.utils.RxUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerContainer: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var viewModel: MainActivityViewModel
    private val bag = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        // setup toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        // setup progressbar
        progressBar = findViewById(R.id.progressBar)

        // setup tabLayout
        tabLayout = findViewById<TabLayout>(R.id.tab_layout).apply {
            addTab(this.newTab().setText(context.getString(R.string.alphabetical_title)))
            addTab(this.newTab().setText(context.getString(R.string.temperature_title)))
            addTab(this.newTab().setText(context.getString(R.string.last_updated_title)))
            tabGravity = TabLayout.GRAVITY_FILL
        }

        viewPagerContainer = findViewById(R.id.pager_container)
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

        // sync weather data
        showLoading(true)
        bag.add(syncWeatherData())

        // pull to refresh
        viewPagerContainer.setOnRefreshListener {
            bag.add(syncWeatherData())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort_descending -> {
                supportFragmentManager.fragments.forEach {
                    (it as? MainFragment)?.setSortOrder(false)
                }
            }
            else -> {
                supportFragmentManager.fragments.forEach {
                    (it as? MainFragment)?.setSortOrder(true)
                }
            }
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!bag.isDisposed) {
            bag.dispose()
        }
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
            viewPagerContainer.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            viewPagerContainer.visibility = View.VISIBLE
        }
    }

    private fun syncWeatherData(): Disposable {
        return viewModel.syncWeatherData().compose(RxUtils.applyIOSchedulers()).subscribe({
            showLoading(false)
            viewPagerContainer.isRefreshing = false
        }, {
            //TODO: handle
        })
    }
}
