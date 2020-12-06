package com.example.coviddata.ui.map

import com.example.coviddata.model.CountryData

class ColorGroupConverter(val listCountries: List<CountryData>) {
    fun getGroupNumber(country: CountryData): Int {
        return when (listCountries.indexOf(country).toDouble()) {
            in listCountries.size * 0.0 .. listCountries.size * 0.1 ->
                1
            in listCountries.size * 0.1 .. listCountries.size * 0.3 ->
                2
            in listCountries.size * 0.3 .. listCountries.size * 0.6 ->
                3
            in listCountries.size * 0.6 .. listCountries.size * 0.8 ->
                4
            else ->
                5
        }
    }

}