package com.alanvan.punters_weather.ui.epoxy

import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyHolder

class EpoxyController<T: EpoxyHolder> : AsyncEpoxyController() {

    private var models: List<BaseEpoxyModel<T>> = ArrayList()

    override fun buildModels() {
        models.forEach {
            it.addTo(this)
        }
    }

    fun setModels(models: List<BaseEpoxyModel<T>>) {
        this.models = models
    }
}