package com.example.coviddata.ui.map

import androidx.lifecycle.ViewModel
import com.example.coviddata.CovidApp
import com.example.coviddata.model.CountryData
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker

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


class ColorGroupConverter(val listCountries: List<CountryData>){
    fun getGroupNumber(country: CountryData): Int {
        val countryMinCases = listCountries.minByOrNull { it.cases }
        return when (country.cases.toDouble()) {
            in countryMinCases!!.cases.toDouble()..countryMinCases.cases * 0.1 ->
                1
            in countryMinCases.cases * 0.1..countryMinCases.cases * 0.3 ->
                2
            in countryMinCases.cases * 0.3..countryMinCases.cases * 0.6 ->
                3
            in countryMinCases.cases * 0.6..countryMinCases.cases * 0.8 ->
                4
            else ->
                5
        }
    }
}


