package com.alanvan.punters_weather.ui.main

import android.os.Bundle
import android.view.View
import com.alanvan.punters_weather.RxFragment
import com.alanvan.punters_weather.utils.RxUtils
import io.reactivex.disposables.CompositeDisposable

abstract class MainFragment : RxFragment() {

    private val bag = CompositeDisposable()
    open val epoxyController = MainEpoxyController()
    open var viewModel: MainViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.let {
            it.loadDataFromRepository(true).map { dataList ->
                epoxyController.setData(dataList)
                epoxyController.requestModelBuild()
            }.compose(RxUtils.applyIOSchedulers()).subscribe({
                // TODO: handle case no data or fail
            }, {
                // TODO: handle case no data or fail
            })
        }?.also {
            bag.add(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!bag.isDisposed) {
            bag.dispose()
        }
    }
}