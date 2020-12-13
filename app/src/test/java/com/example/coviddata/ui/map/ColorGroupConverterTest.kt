package com.example.coviddata.ui.map

import androidx.lifecycle.LiveData
import com.example.coviddata.R
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
        val russia = CountryData(name = "Russia", date = LocalDate.now().toString())
        val usa = CountryData(name = "USA", date = LocalDate.now().toString())
        val canada = CountryData(name = "Canada", date = LocalDate.now().toString())
        val china = CountryData(name = "China", date = LocalDate.now().toString())
        val france = CountryData(name = "France", date = LocalDate.now().toString())
        listCountries = listOf(russia, usa, canada, china, france)
        converter = ColorGroupConverter(listCountries)
    }

    @Test
    fun getMarkerTest() {
        assertEquals(R.drawable.group1mapmarker, converter.getMarker(listCountries[0]))
        assertEquals(R.drawable.group2mapmarker, converter.getMarker(listCountries[1]))
        assertEquals(R.drawable.group3mapmarker, converter.getMarker(listCountries[2]))
        assertEquals(R.drawable.group4mapmarker, converter.getMarker(listCountries[3]))
        assertEquals(R.drawable.group5mapmarker, converter.getMarker(listCountries[4]))
    }

//    @Test
//    fun getGroupNumberTest(){
//        assertEquals(1, converter.getMarkerInfo(listCountries[4]))
//        assertEquals(5, converter.getMarkerInfo(listCountries[1]))
//    }
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
