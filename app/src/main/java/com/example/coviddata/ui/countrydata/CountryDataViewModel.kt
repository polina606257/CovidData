package com.example.coviddata.ui.countrydata

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.coviddata.CovidApp
import com.example.coviddata.datasource.DataResult
import com.example.coviddata.datasource.Repository
import com.example.coviddata.model.CountryData
import com.example.coviddata.ui.BaseViewModel

class CountryDataViewModel @ViewModelInject constructor(val repository: Repository) : BaseViewModel<List<CountryData>>() {
    private var countryName: String? = null
    fun initCountryName(countryName: String) {
        this.countryName = countryName
    }

    init {
        refreshData()
    }

    val countryDataLiveData: LiveData<CountryData>? =
        Transformations.map(mainLiveData){ countries ->
            countries?.find { it.name == countryName }
        }


    private val allCountriesDataHistoryLiveData: LiveData<List<CountryData>> =
        repository.allCountriesHistoryDataLiveData
    val countryDataHistoryLiveData:LiveData<List<CountryData>> =
        Transformations.map(allCountriesDataHistoryLiveData) { historyData ->
            historyData.filter { it.name == countryName }
        }

    override suspend fun getData(): DataResult<List<CountryData>?> {
        return repository.getAllCountriesData()
    }
}
