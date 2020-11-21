package com.example.coviddata.ui.countrydata

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.coviddata.CovidApp
import com.example.coviddata.model.CountryData

class CountryDataViewModel : ViewModel() {
    private var countryName: String? = null
    fun initCountryName(countryName: String) {
        this.countryName = countryName
    }

    private val countriesLiveData = CovidApp.repository.allCountriesDataLastLiveData
    val countryLiveData = Transformations.map(countriesLiveData) { countries ->
        countries.find { it.name == countryName }
    }
    private val allCountriesDataHistoryLiveData: LiveData<List<CountryData>> =
        CovidApp.repository.allCountriesHistoryLiveData
    val countryDataHistoryLiveData =
        Transformations.map(allCountriesDataHistoryLiveData) { historyData ->
            historyData.filter { it.name == countryName }
        }
}
