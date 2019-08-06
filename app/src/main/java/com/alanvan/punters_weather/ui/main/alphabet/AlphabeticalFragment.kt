package com.alanvan.punters_weather.ui.main.alphabet

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyRecyclerView
import com.alanvan.punters_weather.R
import com.alanvan.punters_weather.RxFragment
import com.alanvan.punters_weather.ui.epoxy.BaseEpoxyModel
import com.alanvan.punters_weather.ui.epoxy.EpoxyController
import com.alanvan.punters_weather.ui.epoxy.VenueWeatherEpoxyModel
import com.alanvan.punters_weather.ui.epoxy.VenueWeatherEpoxyModel_
import com.alanvan.punters_weather.utils.RxUtils
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class AlphabeticalFragment : RxFragment() {

    private lateinit var viewModel: AlphabeticalViewModel
    private val bag = CompositeDisposable()
    private val epoxyController = EpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AlphabeticalViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_alphabetical, container, false)
        val recyclerView = view.findViewById<EpoxyRecyclerView>(R.id.recyclerView)
        recyclerView.setController(epoxyController)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bag.add(
            viewModel.loadDataFromRepository(true).map { dataList ->
                epoxyController.setData(dataList)
                epoxyController.requestModelBuild()
            }.compose(RxUtils.applyIOSchedulers()).subscribe({
                // TODO: handle case no data or fail
            }, {
                // TODO: handle case no data or fail
            })
        )
    }
}