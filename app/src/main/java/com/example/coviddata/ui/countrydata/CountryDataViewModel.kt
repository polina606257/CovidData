package com.example.coviddata.ui.countrydata

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.coviddata.CovidApp

class CountryDataViewModel : ViewModel(){
    private var countryName: String? = null
    fun initCountryName(countryName: String){
        this.countryName = countryName
    }

    private val countriesLiveData = CovidApp.repository.countriesLiveData
    val countryLiveData = Transformations.map(countriesLiveData){ countries ->
        countries.find { it.name == countryName }
    }
}