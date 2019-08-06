package com.alanvan.punters_weather.ui.main

import android.os.Bundle
import android.view.View
import com.alanvan.punters_weather.RxFragment
import com.alanvan.punters_weather.utils.RxUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_temperature.*

abstract class MainFragment : RxFragment() {

    private val bag = CompositeDisposable()
    open val epoxyController = MainEpoxyController()
    open var viewModel: MainFragmentViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.let {
            loadDataAndBuildEpoxy(it.sortedAscending)
        }
    }

    private fun loadDataAndBuildEpoxy(sortedAscending: Boolean): Disposable? {
        return viewModel?.let {
            it.loadDataFromRepository(sortedAscending).map { dataList ->
                epoxyController.setData(dataList)
                epoxyController.requestModelBuild()
            }.compose(RxUtils.applyIOSchedulers()).subscribe({
                // TODO: handle case no data or fail
            }, {
                // TODO: handle case no data or fail
            })
        }
    }

    fun setSortOrder(sortedAscending: Boolean) {
        viewModel?.setSortOrder(sortedAscending)
        viewModel?.let {
            loadDataAndBuildEpoxy(it.sortedAscending)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!bag.isDisposed) {
            bag.dispose()
        }
    }
}