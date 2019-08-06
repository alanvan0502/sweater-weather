package com.alanvan.punters_weather.ui.epoxy

import com.airbnb.epoxy.EpoxyModelWithHolder

abstract class BaseEpoxyModel<T : BaseEpoxyModel.Holder> : EpoxyModelWithHolder<T>() {

    open class Holder: KotlinEpoxyHolder()
}