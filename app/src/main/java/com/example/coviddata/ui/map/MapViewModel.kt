package com.example.coviddata.ui.map

import androidx.lifecycle.ViewModel
import com.example.coviddata.CovidApp
import com.example.coviddata.model.CountryData

class MapViewModel : ViewModel() {
    private lateinit var converter: ColorGroupConverter

    val allCountriesLastLiveData = CovidApp.repository.allCountriesDataLastLiveData

    init{
        allCountriesLastLiveData.observeForever{
            converter = ColorGroupConverter(it)
        }
    }

    fun getGroupNumber(country: CountryData) = converter.getGroupNumber(country)
}



