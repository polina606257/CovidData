package com.example.coviddata.ui.countrydata

import androidx.lifecycle.*
import com.example.coviddata.CovidApp
import com.example.coviddata.datasource.DataResult
import com.example.coviddata.datasource.FailureResult
import com.example.coviddata.datasource.FromCacheResult
import com.example.coviddata.datasource.SuccessResult
import com.example.coviddata.model.CountryData
import com.example.coviddata.ui.BaseViewModel
import com.example.coviddata.ui.Event
import kotlinx.coroutines.launch

class CountryDataViewModel : BaseViewModel<List<CountryData>>() {
    private var countryName: String? = null
    fun initCountryName(countryName: String) {
        this.countryName = countryName
    }

    val countryDataLiveData: LiveData<CountryData>? =
        Transformations.map(mainLiveData){ countries ->
            countries?.find { it.name == countryName }
        }


    private val allCountriesDataHistoryLiveData: LiveData<List<CountryData>> =
        CovidApp.repository.allCountriesHistoryDataLiveData
    val countryDataHistoryLiveData:LiveData<List<CountryData>> =
        Transformations.map(allCountriesDataHistoryLiveData) { historyData ->
            historyData.filter { it.name == countryName }
        }

    override suspend fun getData(): DataResult<List<CountryData>?> {
        return CovidApp.repository.getAllCountriesData()
    }
}
