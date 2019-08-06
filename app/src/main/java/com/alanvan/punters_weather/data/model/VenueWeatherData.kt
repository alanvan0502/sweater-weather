package com.alanvan.punters_weather.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VenueWeatherData {

    @SerializedName("_venueID")
    @Expose
    private var venueID: String? = null

    @SerializedName("_name")
    @Expose
    private var name: String? = null

    @SerializedName("_country")
    @Expose
    private var country: Country? = null

    @SerializedName("_weatherCondition")
    @Expose
    private var weatherCondition: String? = null

    @SerializedName("_weatherConditionIcon")
    @Expose
    private var weatherConditionIcon: String? = null

    @SerializedName("_weatherWind")
    @Expose
    private var weatherWind: String? = null

    @SerializedName("_weatherHumidity")
    @Expose
    private var weatherHumidity: String? = null

    @SerializedName("_weatherTemp")
    @Expose
    private var weatherTemp: String? = null

    @SerializedName("_weatherFeelsLike")
    @Expose
    private var weatherFeelsLike: String? = null

    @SerializedName("_sport")
    @Expose
    private var sport: Sport? = null

    @SerializedName("_weatherLastUpdated")
    @Expose
    private var weatherLastUpdated: Int? = null

    fun getVenueID(): String? {
        return venueID
    }

    fun setVenueID(venueID: String) {
        this.venueID = venueID
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getCountry(): Country? {
        return country
    }

    fun setCountry(country: Country) {
        this.country = country
    }

    fun getWeatherCondition(): String? {
        return weatherCondition
    }

    fun setWeatherCondition(weatherCondition: String) {
        this.weatherCondition = weatherCondition
    }

    fun getWeatherConditionIcon(): String? {
        return weatherConditionIcon
    }

    fun setWeatherConditionIcon(weatherConditionIcon: String) {
        this.weatherConditionIcon = weatherConditionIcon
    }

    fun getWeatherWind(): String? {
        return weatherWind
    }

    fun setWeatherWind(weatherWind: String) {
        this.weatherWind = weatherWind
    }

    fun getWeatherHumidity(): String? {
        return weatherHumidity
    }

    fun setWeatherHumidity(weatherHumidity: String) {
        this.weatherHumidity = weatherHumidity
    }

    fun getWeatherTemp(): String? {
        return weatherTemp
    }

    fun setWeatherTemp(weatherTemp: String) {
        this.weatherTemp = weatherTemp
    }

    fun getWeatherFeelsLike(): String? {
        return weatherFeelsLike
    }

    fun setWeatherFeelsLike(weatherFeelsLike: String) {
        this.weatherFeelsLike = weatherFeelsLike
    }

    fun getSport(): Sport? {
        return sport
    }

    fun setSport(sport: Sport) {
        this.sport = sport
    }

    fun getWeatherLastUpdated(): Int? {
        return weatherLastUpdated
    }

    fun setWeatherLastUpdated(weatherLastUpdated: Int?) {
        this.weatherLastUpdated = weatherLastUpdated
    }
}