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
class ColorGroupConverter(val listCountries: List<CountryData>){
    fun getGroupNumber(country: CountryData): Int {
        return when (country.casesPerOneMillion) {
            in getCasesRelatedForGroup(0.0, 0.1) ->
                1
            in getCasesRelatedForGroup(0.1, 0.3) ->
                2
            in getCasesRelatedForGroup(0.3, 0.6) ->
                3
            in getCasesRelatedForGroup(0.6, 0.8) ->
                4
            else ->
                5
        }
    }

    private fun getCasesRelatedForGroup(percentForMin: Double, percentForMax: Double) : ClosedFloatingPointRange<Double> {
        val countryMinCasesPerOneMillion = listCountries.minByOrNull { it.casesPerOneMillion }
        val countryMaxCasesPerOneMillion = listCountries.maxByOrNull { it.casesPerOneMillion }
        val minNumCasesPerGroup = (countryMaxCasesPerOneMillion!!.cases - countryMinCasesPerOneMillion!!
                .cases)* percentForMin
        val maxNumCasesPerGroup = (countryMaxCasesPerOneMillion.cases - countryMinCasesPerOneMillion
                .cases) * percentForMax
        return minNumCasesPerGroup..(maxNumCasesPerGroup)
    }
}




