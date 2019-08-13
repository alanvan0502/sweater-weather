package com.alanvan.sweater_weather.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName

@Entity(tableName = Country.TABLE_NAME)
class Country {

    companion object {
        const val TABLE_NAME = "Country"
        const val COUNTRY_ID = "_countryID"
        const val NAME = "_name"
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COUNTRY_ID)
    @SerializedName("_countryID")
    private lateinit var countryID: String

    @ColumnInfo(name = NAME)
    @SerializedName("_name")
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

    fun setName(name: String?) {
        this.name = name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Country) return false

        if (countryID != other.countryID) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = countryID?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }
}