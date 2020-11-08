package com.example.covidappapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CountryCases(
        @PrimaryKey
        @SerializedName(value = "country")val name: String,
        val flag: String,
        val cases: Int,
        val deaths: Int,
        val recovered: Int,
        val casesPerOneMillion: Int,
        val deathsPerOneMillion: Int,
        val testsPerOneMillion: Int
)