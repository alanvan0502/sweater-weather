package com.alanvan.punters_weather.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data {

    @SerializedName("ret")
    @Expose
    private var ret: Boolean? = null

    @SerializedName("isOkay")
    @Expose
    private var isOkay: Boolean? = null

    @SerializedName("data")
    @Expose
    private var data: List<VenueWeatherData>? = null

    fun getRet(): Boolean? {
        return ret
    }

    fun setRet(ret: Boolean?) {
        this.ret = ret
    }

    fun getIsOkay(): Boolean? {
        return isOkay
    }

    fun setIsOkay(isOkay: Boolean?) {
        this.isOkay = isOkay
    }

    fun getData(): List<VenueWeatherData>? {
        return data
    }

    fun setData(data: List<VenueWeatherData>) {
        this.data = data
    }
}