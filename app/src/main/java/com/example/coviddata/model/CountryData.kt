package com.example.coviddata.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CountryData(
        @PrimaryKey
        @SerializedName(value = "country")
        val name: String,
        @Embedded(prefix = "countryInfo")
        val countryInfo: CountryInfo = CountryInfo(""),
        val cases: Int,
        val deaths: Int = 0,
        val recovered: Int = 0,
        val casesPerOneMillion: Double = 0.0,
        val deathsPerOneMillion: Double = 0.0,
        val testsPerOneMillion: Double = 0.0
)

data class CountryInfo(
        val flag: String
)