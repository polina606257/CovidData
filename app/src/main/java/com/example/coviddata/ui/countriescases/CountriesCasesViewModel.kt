 package com.example.coviddata.ui.countriescases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidappapi.model.CountryCases
import com.example.coviddata.CovidApp

enum class SortParam {
    NAME, CASES
}
class CountriesCasesViewModel : ViewModel() {
    var sortParam: SortParam = SortParam.NAME
        set(value){
            field = value
            _countriesLiveData.value = prepareListCountries()
        }
    var filterParam: String = ""
        set(value){
            field = value
            _countriesLiveData.value = prepareListCountries()
        }

    private val countriesLiveDataFromRepository = CovidApp.repository.countriesLiveData

    init {
        countriesLiveDataFromRepository.observeForever {
            _countriesLiveData.value = prepareListCountries()
        }
    }
    private val _countriesLiveData = MutableLiveData<List<CountryCases>>()
    val countriesLiveData: LiveData<List<CountryCases>> = _countriesLiveData

    private fun prepareListCountries(): List<CountryCases>?{
        val countries = countriesLiveDataFromRepository.value
        val filteredCountries = if (filterParam != "")
            countries?.filter { it.name.startsWith(filterParam, true) }
        else
            countries
        return when(sortParam){
            SortParam.NAME -> filteredCountries?.sortedBy { it.name }
            SortParam.CASES -> filteredCountries?.sortedBy { it.cases }
        }
    }

    fun refreshCountries() {
        CovidApp.repository.refreshCountriesCases()
    }
}
