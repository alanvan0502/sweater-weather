package com.alanvan.punters_weather

import android.arch.lifecycle.ViewModel
import com.alanvan.punters_weather.ui.epoxy.BaseEpoxyModel
import io.reactivex.Observable

abstract class BaseViewModel: ViewModel() {

    abstract fun setupView(fragment: BaseFragment): Observable<List<BaseEpoxyModel>>

}