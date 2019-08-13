package com.alanvan.sweater_weather.ui.main

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import com.alanvan.sweater_weather.R
import com.alanvan.sweater_weather.ui.main.filter.FilterDialogFragment
import com.alanvan.sweater_weather.utils.RxUtils
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.github.pwittchen.reactivenetwork.library.rx2.internet.observing.InternetObservingSettings
import com.github.pwittchen.reactivenetwork.library.rx2.internet.observing.strategy.SocketInternetObservingStrategy
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerContainer: SwipeRefreshLayout
    private lateinit var filterButtonContainer: LinearLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var pagerAdapter: PagerAdapter
    private lateinit var viewModel: MainActivityViewModel

    var sortedAscending: Boolean?
        get() {
            return viewModel.sortedAscending
        }
        set(value) {
            viewModel.sortedAscending = value
        }

    var countryId: String?
        get() {
            return viewModel.countryId
        }
        set(value) {
            viewModel.countryId = value
        }

    private var showLoading = false

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
        tabLayout = findViewById(R.id.tab_layout)

        viewPagerContainer = findViewById(R.id.pager_container)
        viewPager = findViewById(R.id.pager)
        pagerAdapter = PagerAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = pagerAdapter

        val pageChangeListener = TabLayoutPageChangeListener(tabLayout, viewPagerContainer)

        viewPager.addOnPageChangeListener(pageChangeListener)
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

        // setup filter button
        filterButtonContainer = findViewById(R.id.action_filter)
        filterButtonContainer.setOnClickListener {
            if (showLoading || viewPagerContainer.isRefreshing) {
                return@setOnClickListener
            }
            // add filter dialog fragment here
            val filterFragment = FilterDialogFragment.newInstance()
            filterFragment.show(supportFragmentManager, FilterDialogFragment::class.java.name)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort_descending -> viewModel.sortedAscending = false
            R.id.action_sort_ascending -> viewModel.sortedAscending = true
            R.id.action_clear_sort -> viewModel.sortedAscending = null
            R.id.action_clear_filter -> viewModel.countryId = null
            else -> {
                // not applicable
            }
        }
        supportFragmentManager.fragments.forEach {
            (it as? MainFragment)?.loadDataAndBuildModel()
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
        showLoading = show
        if (show) {
            progressBar.visibility = View.VISIBLE
            viewPagerContainer.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            viewPagerContainer.visibility = View.VISIBLE
        }
    }

    private fun syncWeatherData(): CompositeDisposable {

        val compositeDisposable = CompositeDisposable()

        val settings = InternetObservingSettings.builder()
            .host("http://dnu5embx6omws.cloudfront.net")
            .strategy(SocketInternetObservingStrategy())
            .build()

        compositeDisposable.add(ReactiveNetwork.observeInternetConnectivity(settings)
            .compose(RxUtils.applyIOSchedulers())
            .subscribe({ isConnectedToHost ->
                if (isConnectedToHost) {
                    compositeDisposable.add(
                        viewModel.syncWeatherData().compose(RxUtils.applyIOSchedulers()).subscribe(
                            {
                                showLoading(false)
                                viewPagerContainer.isRefreshing = false
                            },
                            {
                                Log.d("syncWeatherData()", "error")
                            })
                    )
                } else {
                    Toast.makeText(this, "No internet connection!", Toast.LENGTH_SHORT).show()
                    showLoading(false)
                    viewPagerContainer.isRefreshing = false
                }
            }, {
                Log.d("syncWeatherData()", "error checking reachability")
            })
        )

        return compositeDisposable
    }
}
