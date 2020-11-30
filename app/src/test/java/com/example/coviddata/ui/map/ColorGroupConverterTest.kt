package com.example.coviddata.ui.map

import com.example.coviddata.model.CountryData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class ColorGroupConverterTest{
    lateinit var converter: ColorGroupConverter
    lateinit var listCountries: List<CountryData>
    @Before
    fun before(){
        val russia = CountryData(name = "Russia", casesPerOneMillion = 1000.0, date = LocalDate.now().toString())
        val usa = CountryData(name = "USA", casesPerOneMillion = 100000.0, date = LocalDate.now().toString())
        val canada = CountryData(name = "Canada", casesPerOneMillion = 30000.0, date = LocalDate.now().toString())
        val china = CountryData(name = "China", casesPerOneMillion = 600.0, date = LocalDate.now().toString())
        val france = CountryData(name = "France", casesPerOneMillion = 10.0, date = LocalDate.now().toString())
        listCountries = listOf(russia, usa, canada, china, france)
        converter = ColorGroupConverter(listCountries)
    }

    @Test
    fun getGroupNumberTest(){
        assertEquals(1, converter.getGroupNumber(listCountries[4]))
        assertEquals(5, converter.getGroupNumber(listCountries[1]))
    }
}



/*
private fun getCasesRelatedForGroup(percentForMin: Double, percentForMax: Double) : ClosedFloatingPointRange<Double> {
    val countryMinCasesPerOneMillion = listCountries.minByOrNull { it.casesPerOneMillion }
    val countryMaxCasesPerOneMillion = listCountries.maxByOrNull { it.casesPerOneMillion }
    val minNumCasesPerGroup = (countryMaxCasesPerOneMillion!!.cases - countryMinCasesPerOneMillion!!
            .cases)* percentForMin
    val maxNumCasesPerGroup = (countryMaxCasesPerOneMillion.cases - countryMinCasesPerOneMillion
            .cases) * percentForMax
    return minNumCasesPerGroup..(maxNumCasesPerGroup)
}*/

/*fun getGroupNumber(country: CountryData): Int {
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
}*/
