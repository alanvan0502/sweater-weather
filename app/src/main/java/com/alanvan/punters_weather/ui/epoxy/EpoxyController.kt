package com.alanvan.punters_weather.ui.epoxy

import com.airbnb.epoxy.AsyncEpoxyController

class EpoxyController : AsyncEpoxyController() {

    private var models: List<BaseEpoxyModel> = ArrayList()

    override fun buildModels() {
        models.forEach {
            it.addTo(this)
        }
    }

    fun setModels(models: List<BaseEpoxyModel>) {
        this.models = models
    }
}