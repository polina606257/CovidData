package com.example.coviddata.ui.map

import com.example.coviddata.R
import com.example.coviddata.model.CountryData

class ColorGroupConverter(val listCountries: List<CountryData>) {
    data class MarkerInfo(
        val markerId: Int,
        val markerTitle: Int,
        val number: Double
    )
    fun getMarkerId(country: CountryData): Int {
        return when (listCountries.indexOf(country).toDouble()) {
            in listCountries.size * 0.0 .. listCountries.size * 0.1 ->
                R.drawable.group1mapmarker
            in listCountries.size * 0.1 .. listCountries.size * 0.3 ->
                R.drawable.group2mapmarker
            in listCountries.size * 0.3 .. listCountries.size * 0.6 ->
                R.drawable.group3mapmarker
            in listCountries.size * 0.6 .. listCountries.size * 0.8 ->
                R.drawable.group4mapmarker
            else ->
                R.drawable.group5mapmarker
        }
    }

//    private fun getCasesRelatedForGroup(percentForMin: Double, percentForMax: Double) : ClosedFloatingPointRange<Double> {
//        val countriesListSize = listCountries.size
//        val minNumPerGroup = countriesListSize * percentForMin
//        val maxNumPerGroup = countriesListSize * percentForMax
//        return minNumPerGroup..maxNumPerGroup
//    }
//        val countryMinCasesPerOneMillion = listCountries.minByOrNull { it.casesPerOneMillion }
//        val countryMaxCasesPerOneMillion = listCountries.maxByOrNull { it.casesPerOneMillion }
//        val minNumCasesPerGroup = (countryMaxCasesPerOneMillion!!.casesPerOneMillion - countryMinCasesPerOneMillion!!
//                .casesPerOneMillion)* percentForMin
//        val maxNumCasesPerGroup = (countryMaxCasesPerOneMillion.casesPerOneMillion - countryMinCasesPerOneMillion
//                .casesPerOneMillion) * percentForMax
//        return minNumCasesPerGroup..(maxNumCasesPerGroup)
//    }
}