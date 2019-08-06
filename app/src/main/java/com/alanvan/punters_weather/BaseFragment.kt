package com.alanvan.punters_weather

import com.alanvan.punters_weather.ui.epoxy.EpoxyController

abstract class BaseFragment : RxFragment() {

    abstract fun getEpoxyController(): EpoxyController

}