package com.example.coviddata.ui.map

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.coviddata.CovidApp
import com.example.coviddata.datasource.DataResult
import com.example.coviddata.datasource.Repository
import com.example.coviddata.model.CountryData
import com.example.coviddata.ui.BaseViewModel

enum class SortParamMap {
    CASES, DEATHS, RECOVERED, CASES_PER_MILLION, DEATHS_PER_MILLION, TEST_PER_MILLION
}

class MapViewModel @ViewModelInject constructor(val repository: Repository) : BaseViewModel<List<CountryData>>() {
    lateinit var converter: ColorGroupConverter

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
                converter = ColorGroupConverter(it, sortParamLiveData)
            }
        }
    }

    fun getMarkerInfo(country: CountryData ): ColorGroupConverter.MarkerInfo = converter.getMarkerInfo(country )

    override suspend fun getData(): DataResult<List<CountryData>?> {
         return repository.getAllCountriesData()
    }
}



