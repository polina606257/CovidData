package com.example.coviddata.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["date","name"])
data class CountryData(
        var date: String,
        @SerializedName(value = "country")
        val name: String,
        @Embedded(prefix = "countryInfo")
        val countryInfo: CountryInfo = CountryInfo(),
        val cases: Int = 0,
        val deaths: Int = 0,
        val recovered: Int = 0,
        val casesPerOneMillion: Double = 0.0,
        val deathsPerOneMillion: Double = 0.0,
        val testsPerOneMillion: Double = 0.0
)

data class CountryInfo(
        var lat: Double = 0.0,
        var long: Double = 0.0,
        var flag: String = ""
)
