package com.alanvan.sweater_weather.ui.main.event

import io.reactivex.subjects.PublishSubject

class PublishFilterEvents private constructor() {

    companion object {
        val instance = PublishFilterEvents()
    }

    val subject: PublishSubject<BaseFilterEvent> = PublishSubject.create()
}

abstract class BaseFilterEvent(val countryId: String? = null)

class FilterEvent(countryId: String?) : BaseFilterEvent(countryId)