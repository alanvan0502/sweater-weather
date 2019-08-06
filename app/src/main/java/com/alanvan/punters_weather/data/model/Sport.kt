package com.alanvan.punters_weather.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sport {

    @SerializedName("_sportID")
    @Expose
    private var sportID: String? = null

    @SerializedName("_description")
    @Expose
    private var description: String? = null

    fun getSportID(): String? {
        return sportID
    }

    fun setSportID(sportID: String) {
        this.sportID = sportID
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }
}