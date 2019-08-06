package com.alanvan.punters_weather.ui.epoxy

import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.alanvan.punters_weather.RxFragment

abstract class BaseEpoxyModel<T : EpoxyHolder>(val fragment: RxFragment) : EpoxyModelWithHolder<T>()