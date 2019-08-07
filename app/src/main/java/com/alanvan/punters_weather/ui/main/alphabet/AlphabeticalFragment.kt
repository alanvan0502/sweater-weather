package com.alanvan.punters_weather.ui.main.alphabet

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyRecyclerView
import com.alanvan.punters_weather.R
import com.alanvan.punters_weather.RxFragment
import com.alanvan.punters_weather.ui.main.MainEpoxyController
import com.alanvan.punters_weather.ui.main.MainFragment
import com.alanvan.punters_weather.utils.RxUtils
import io.reactivex.disposables.CompositeDisposable

class AlphabeticalFragment : MainFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AlphabeticalViewModel::class.java)
    }

}