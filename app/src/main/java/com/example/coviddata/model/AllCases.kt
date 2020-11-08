package com.example.covidappapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AllCases(
    val cases: Int,
    val deaths: Int,
    val recovered: Int,
    @PrimaryKey
    var datetime: String
)