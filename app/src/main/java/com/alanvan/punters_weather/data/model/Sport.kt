package com.alanvan.punters_weather.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = Sport.TABLE_NAME)
class Sport {

    companion object {
        const val TABLE_NAME = "Sport"
        const val COLUMN_SPORT_ID = "_sportID"
        const val COLUMN_DESCRIPTION = "_description"
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COLUMN_SPORT_ID)
    @SerializedName("_sportID")
    @Expose
    private lateinit var sportID: String

    @ColumnInfo(name = COLUMN_DESCRIPTION)
    @SerializedName("_description")
    @Expose
    private var description: String? = null

    fun getSportID(): String {
        return sportID
    }

    fun setSportID(sportID: String) {
        this.sportID = sportID
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Sport) return false

        if (sportID != other.sportID) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = sportID?.hashCode() ?: 0
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }


}