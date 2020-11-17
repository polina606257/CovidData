 package com.example.coviddata.ui.countriescases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covidappapi.model.CountryCases
import com.example.coviddata.CovidApp

enum class SortParam {
    NAME, CASES
}

class CountriesCasesViewModel : ViewModel() {

    var sortParamLiveData: MutableLiveData<SortParam> = MutableLiveData(SortParam.NAME)
    var filterParamLiveData: MutableLiveData<String> = MutableLiveData<String>("")
    private val countriesLiveDataFromRepository = CovidApp.repository.countriesLiveData
    val mediatorLiveData = MediatorLiveData<List<CountryCases>>()

    init {
        mediatorLiveData.addSource(sortParamLiveData) { val listCountries = prepareListCountries() ->
            mediatorLiveData.setValue(listCountries)}
        mediatorLiveData.addSource(filterParamLiveData) {val listCountries = prepareListCountries() ->
            mediatorLiveData.setValue(listCountries) }
        mediatorLiveData.addSource(countriesLiveDataFromRepository) {countriesLiveDataFromRepository.value ->
            mediatorLiveData.setValue(countriesLiveDataFromRepository.value)}
    }

    private fun prepareListCountries(): List<CountryCases>?{
        val countries = countriesLiveDataFromRepository.value
        val filteredCountries = if (filterParamLiveData.value != "")
            countries?.filter { it.name.startsWith(filterParamLiveData.value!!, true) }
        else
            countries
        return when(sortParamLiveData.value!!){
            SortParam.NAME -> filteredCountries?.sortedBy { it.name }
            SortParam.CASES -> filteredCountries?.sortedBy { it.cases }
        }
    }

    fun refreshCountries() {
        CovidApp.repository.refreshCountriesCases()
    }
}

