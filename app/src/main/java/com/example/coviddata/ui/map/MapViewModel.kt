package com.example.coviddata.ui.map

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.coviddata.R
import com.example.coviddata.datasource.DataResult
import com.example.coviddata.datasource.Repository
import com.example.coviddata.model.CountryData
import com.example.coviddata.ui.BaseViewModel

enum class SortParamMap {
    CASES, DEATHS, RECOVERED, CASES_PER_MILLION, DEATHS_PER_MILLION, TEST_PER_MILLION
}

class MapViewModel @ViewModelInject constructor(val repository: Repository) : BaseViewModel<List<CountryData>>() {
    lateinit var converter: ColorGroupConverter

    init {
        refreshData()
    }

    val sortParamLiveData: MutableLiveData<SortParamMap> = MutableLiveData(SortParamMap.CASES)

    val countriesLiveData = MediatorLiveData<List<CountryData>>().apply {
        addSource(sortParamLiveData) {
            value = prepareListCountries()
        }
        addSource(mainLiveData) {
            value = prepareListCountries()
        }
    }

    private fun prepareListCountries(): List<CountryData>?{
        val countries = mainLiveData.value
        return when(sortParamLiveData.value!!){
            SortParamMap.CASES -> countries?.sortedBy { it.cases }
            SortParamMap.DEATHS -> countries?.sortedBy { it.deaths }
            SortParamMap.RECOVERED -> countries?.sortedBy { it.recovered }
            SortParamMap.CASES_PER_MILLION -> countries?.sortedBy { it.casesPerOneMillion }
            SortParamMap.DEATHS_PER_MILLION -> countries?.sortedBy { it.deathsPerOneMillion }
            SortParamMap.TEST_PER_MILLION -> countries?.sortedBy { it.testsPerOneMillion }
        }
    }

    init{
        countriesLiveData.observeForever{
            it?.let{
                converter = ColorGroupConverter(it)
            }
        }
    }

    fun getMarker(country: CountryData ): Int = converter.getMarker(country )

    override suspend fun getData(): DataResult<List<CountryData>?> {
         return repository.getAllCountriesData()
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
        var number: Long = when (sortParamMap.ordinal) {
            SortParamMap.CASES.ordinal -> country.cases.toLong()
            SortParamMap.DEATHS.ordinal -> country.deaths.toLong()
            SortParamMap.RECOVERED.ordinal -> country.recovered.toLong()
            SortParamMap.CASES_PER_MILLION.ordinal -> Math.round(country.casesPerOneMillion)
            SortParamMap.DEATHS_PER_MILLION.ordinal -> Math.round(country.deathsPerOneMillion)
            else -> Math.round(country.testsPerOneMillion)
        }
          return number
        }
}



