package com.alanvan.punters_weather.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName

@Entity(tableName = VenueWeatherData.TABLE_NAME)
class VenueWeatherData {

    companion object {
        const val TABLE_NAME = "VenueWeatherData"
        const val COLUMN_VENUE_ID = "_venueID"
        const val COLUMN_NAME = "_name"
        const val COLUMN_COUNTRY_ID = "_countryID"
        const val COLUMN_WEATHER_CONDITION = "_weatherCondition"
        const val COLUMN_WEATHER_CONDITION_ICON = "_weatherConditionIcon"
        const val COLUMN_WEATHER_WIND = "_weatherWind"
        const val COLUMN_WEATHER_HUMIDITY = "_weatherHumidity"
        const val COLUMN_WEATHER_TEMP = "_weatherTemp"
        const val COLUMN_WEATHER_FEELS_LIKE = "_weatherFeelsLike"
        const val COLUMN_SPORT_ID = "_sportID"
        const val COLUMN_WEATHER_LAST_UPDATED = "_weatherLastUpdated"
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COLUMN_VENUE_ID)
    @SerializedName("_venueID")
    private lateinit var venueID: String

    @ColumnInfo(name = COLUMN_NAME)
    @SerializedName("_name")
    private var name: String? = null

    @Ignore
    @SerializedName("_country")
    private var country: Country? = null

    @ColumnInfo(name = COLUMN_COUNTRY_ID)
    private var countryID: String? = null

    @ColumnInfo(name = COLUMN_WEATHER_CONDITION)
    @SerializedName("_weatherCondition")
    private var weatherCondition: String? = null

    @ColumnInfo(name = COLUMN_WEATHER_CONDITION_ICON)
    @SerializedName("_weatherConditionIcon")
    private var weatherConditionIcon: String? = null

    @ColumnInfo(name = COLUMN_WEATHER_WIND)
    @SerializedName("_weatherWind")
    private var weatherWind: String? = null

    @ColumnInfo(name = COLUMN_WEATHER_HUMIDITY)
    @SerializedName("_weatherHumidity")
    private var weatherHumidity: String? = null

    @ColumnInfo(name = COLUMN_WEATHER_TEMP)
    @SerializedName("_weatherTemp")
    private var weatherTemp: Int? = null

    @ColumnInfo(name = COLUMN_WEATHER_FEELS_LIKE)
    @SerializedName("_weatherFeelsLike")
    private var weatherFeelsLike: String? = null

    @Ignore
    @SerializedName("_sport")
    private var sport: Sport? = null

    @ColumnInfo(name = COLUMN_SPORT_ID)
    private var sportID: String? = null

    @ColumnInfo(name = COLUMN_WEATHER_LAST_UPDATED)
    @SerializedName("_weatherLastUpdated")
    private var weatherLastUpdated: Int? = null

    fun getVenueID(): String {
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

    fun setCountryID(countryID: String) {
        this.countryID = countryID
    }

    fun getCountryID(): String? {
        return countryID
    }

    fun getWeatherCondition(): String? {
        return weatherCondition
    }

    fun setWeatherCondition(weatherCondition: String?) {
        this.weatherCondition = weatherCondition
    }

    fun getWeatherConditionIcon(): String? {
        return weatherConditionIcon
    }

    fun setWeatherConditionIcon(weatherConditionIcon: String?) {
        this.weatherConditionIcon = weatherConditionIcon
    }

    fun getWeatherWind(): String? {
        return weatherWind
    }

    fun setWeatherWind(weatherWind: String?) {
        this.weatherWind = weatherWind
    }

    fun getWeatherHumidity(): String? {
        return weatherHumidity
    }

    fun setWeatherHumidity(weatherHumidity: String?) {
        this.weatherHumidity = weatherHumidity
    }

    fun getWeatherTemp(): Int? {
        return weatherTemp
    }

    fun setWeatherTemp(weatherTemp: Int?) {
        this.weatherTemp = weatherTemp
    }

    fun getWeatherFeelsLike(): String? {
        return weatherFeelsLike
    }

    fun setWeatherFeelsLike(weatherFeelsLike: String?) {
        this.weatherFeelsLike = weatherFeelsLike
    }

    fun getSport(): Sport? {
        return sport
    }

    fun setSport(sport: Sport) {
        this.sport = sport
    }

    fun setSportID(sportID: String) {
        this.sportID = sportID
    }

    fun getSportID(): String? {
        return sportID
    }

    fun getWeatherLastUpdated(): Int? {
        return weatherLastUpdated
    }

    fun setWeatherLastUpdated(weatherLastUpdated: Int?) {
        this.weatherLastUpdated = weatherLastUpdated
    }
}