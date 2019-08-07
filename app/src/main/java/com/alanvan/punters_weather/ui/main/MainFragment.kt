package com.alanvan.punters_weather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyRecyclerView
import com.alanvan.punters_weather.R
import com.alanvan.punters_weather.RxFragment
import com.alanvan.punters_weather.ui.main.event.FilterEvent
import com.alanvan.punters_weather.ui.main.event.PublishFilterEvents
import com.alanvan.punters_weather.utils.RxUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class MainFragment : RxFragment() {

    private val bag = CompositeDisposable()
    open val epoxyController = MainEpoxyController()
    open var viewModel: MainFragmentViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Listen to filter events & clear filter events
        bag.add(
            PublishFilterEvents.instance.subject.compose(RxUtils.applyIOSchedulers()).subscribe {
                if (it is FilterEvent) {
                    (activity as? MainActivity)?.countryId = it.countryId
                    loadDataAndBuildModel()?.let { disposable ->
                        bag.add(disposable)
                    }
                }
            }
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        val recyclerView: EpoxyRecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setController(epoxyController)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDataAndBuildModel()?.let { disposable ->
            bag.add(disposable)
        }
    }

    fun loadDataAndBuildModel(): Disposable? {
        val sortedAscending = (activity as? MainActivity)?.sortedAscending
        val countryId = (activity as? MainActivity)?.countryId

        return if (sortedAscending == null) {
            loadDataAndBuildEpoxyUnSorted(countryId)
        } else {
            loadDataAndBuildEpoxySorted(sortedAscending, countryId)
        }
    }

    private fun loadDataAndBuildEpoxyUnSorted(countryId: String?): Disposable? {
        return viewModel?.loadDataFromRepositoryByCountryId(countryId)?.map { dataList ->
            epoxyController.setData(dataList)
            epoxyController.requestModelBuild()
        }?.compose(RxUtils.applyIOSchedulers())?.subscribe()
    }

    private fun loadDataAndBuildEpoxySorted(sortedAscending: Boolean, countryId: String? = null): Disposable? {
        return viewModel?.loadDataFromRepository(sortedAscending, countryId)?.map { dataList ->
            epoxyController.setData(dataList)
            epoxyController.requestModelBuild()
        }?.compose(RxUtils.applyIOSchedulers())?.subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!bag.isDisposed) {
            bag.dispose()
        }
    }
}