package com.alanvan.punters_weather.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Country {

    @SerializedName("_countryID")
    @Expose
    private var countryID: String? = null

    @SerializedName("_name")
    @Expose
    private var name: String? = null

    fun getCountryID(): String? {
        return countryID
    }

    fun setCountryID(countryID: String) {
        this.countryID = countryID
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }
}