package com.example.coviddata.ui.map

import androidx.lifecycle.LiveData
import com.example.coviddata.R
import com.example.coviddata.model.CountryData

class ColorGroupConverter(val listCountries: List<CountryData>) {

    data class MarkerInfo(
        val markerId: Int,
        val markerTitle: Int,
        val markerNumber: Long,
    )

    fun getMarkerInfo(country: CountryData, sortParamMap: SortParamMap): MarkerInfo {
        val index = listCountries.indexOf(country).toDouble()
        return when {
            (index >= listCountries.size * 0.0) && (index < listCountries.size * 0.1) ->
                MarkerInfo(
                    R.drawable.group1mapmarker, getTitle(sortParamMap),
                    getNumber(country, sortParamMap)
                )
            (index >= listCountries.size * 0.1) && (index < listCountries.size * 0.3) ->
                MarkerInfo(
                    R.drawable.group2mapmarker, getTitle(sortParamMap),
                    getNumber(country, sortParamMap)
                )
            (index >= listCountries.size * 0.3) && (index < listCountries.size * 0.6) ->
                MarkerInfo(
                    R.drawable.group3mapmarker, getTitle(sortParamMap),
                    getNumber(country, sortParamMap)
                )
            (index >= listCountries.size * 0.6) && (index < listCountries.size * 0.8) ->
                MarkerInfo(
                    R.drawable.group4mapmarker, getTitle(sortParamMap),
                    getNumber(country, sortParamMap)
                )
            else ->
                MarkerInfo(
                    R.drawable.group5mapmarker, getTitle(sortParamMap),
                    getNumber(country, sortParamMap)
                )
        }
    }

    fun getTitle(sortParamMap: SortParamMap): Int {
        return when (sortParamMap.ordinal) {
            SortParamMap.CASES.ordinal -> R.string.cases_title
            SortParamMap.DEATHS.ordinal -> R.string.deaths_title
            SortParamMap.RECOVERED.ordinal -> R.string.recovered_title
            SortParamMap.CASES_PER_MILLION.ordinal -> R.string.cases_per_one_million_title
            SortParamMap.DEATHS_PER_MILLION.ordinal -> R.string.deaths_per_one_million_title
            else -> R.string.tests_per_one_million_title
        }
    }


    fun getNumber(country: CountryData, sortParamMap: SortParamMap): Long {
        return when (sortParamMap.ordinal) {
            SortParamMap.CASES.ordinal -> country.cases.toLong()
            SortParamMap.DEATHS.ordinal -> country.deaths.toLong()
            SortParamMap.RECOVERED.ordinal -> country.recovered.toLong()
            SortParamMap.CASES_PER_MILLION.ordinal -> Math.round(country.casesPerOneMillion)
            SortParamMap.DEATHS_PER_MILLION.ordinal -> Math.round(country.deathsPerOneMillion)
            else -> Math.round(country.testsPerOneMillion)
        }
    }
}