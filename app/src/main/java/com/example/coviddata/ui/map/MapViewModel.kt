package com.example.coviddata.ui.map

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.coviddata.CovidApp
import com.example.coviddata.model.CountryData
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker

class MapViewModel : ViewModel() {
    private var googleMap: GoogleMap? = null
    fun initGoogleMap(googleMap: GoogleMap) {
        this.googleMap = googleMap
    }

    val allCountriesLastLiveData = CovidApp.repository.allCountriesDataLastLiveData

    fun getPinColor(listCountries: List<CountryData>) : BitmapDescriptor {
        val countryMinCases = allCountriesLastLiveData.value?.minByOrNull { it.cases }

        var colorPin: BitmapDescriptor = defaultMarker(BitmapDescriptorFactory.HUE_RED)

            for(country in listCountries)
                when(country.cases.toDouble()) {
                    in countryMinCases!!.cases.toDouble() .. countryMinCases.cases * 0.1 ->
                        colorPin = defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                    in countryMinCases.cases * 0.1 .. countryMinCases.cases * 0.3 ->
                        colorPin = defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)
                    in countryMinCases.cases * 0.3 .. countryMinCases.cases * 0.6 ->
                        colorPin = defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                    in countryMinCases.cases * 0.6 .. countryMinCases.cases * 0.8 ->
                        defaultMarker(BitmapDescriptorFactory.HUE_ROSE)
                    else ->
                        colorPin = defaultMarker(BitmapDescriptorFactory.HUE_RED)
                }
            return colorPin
        }
    }
