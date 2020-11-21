package com.example.coviddata.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WorldData(
    val cases: Int,
    val deaths: Int,
    val recovered: Int,
    @PrimaryKey
    var date: String
)