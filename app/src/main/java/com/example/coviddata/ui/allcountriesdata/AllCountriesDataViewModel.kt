 package com.example.coviddata.ui.allcountriesdata

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coviddata.model.CountryData
import com.example.coviddata.CovidApp

enum class SortParam {
    NAME, CASES
}

class CountriesCasesViewModel : ViewModel() {
    private var _listener: Listener? = null
    fun setListener(listener: Listener){
        _listener = listener
    }
    interface Listener{
        fun onShowCountryDetails(countryData: CountryData)
    }

    val sortParamLiveData: MutableLiveData<SortParam> = MutableLiveData(SortParam.NAME)
    val filterParamLiveData: MutableLiveData<String> = MutableLiveData<String>("")
    private val countriesLiveDataFromRepository = CovidApp.repository.countriesLiveData
    val countriesLiveData = MediatorLiveData<List<CountryData>>().apply {
        addSource(sortParamLiveData) {
            value = prepareListCountries()
        }
        addSource(filterParamLiveData) {
            value = prepareListCountries()
        }
        addSource(countriesLiveDataFromRepository) {
            value = prepareListCountries()
        }
    }

    private fun prepareListCountries(): List<CountryData>?{
        val countries = countriesLiveDataFromRepository.value
        val filteredCountries = if (filterParamLiveData.value != "")
            countries?.filter { it.name.startsWith(filterParamLiveData.value!!, true) }
        else
            countries
        return when(sortParamLiveData.value!!){
            SortParam.NAME -> filteredCountries?.sortedBy { it.name }
            SortParam.CASES -> filteredCountries?.sortedByDescending { it.cases }
        }
    }

    fun refreshCountries() {
        CovidApp.repository.refreshCountriesCases()
    }

    fun showCountryDetails(countryData: CountryData){
        _listener?.let {
            it.onShowCountryDetails(countryData)
        }
    }
}

