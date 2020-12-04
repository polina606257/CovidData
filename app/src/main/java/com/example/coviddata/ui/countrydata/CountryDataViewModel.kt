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

class CountryDataViewModel : BaseViewModel() {
    private var countryName: String? = null
    fun initCountryName(countryName: String) {
        this.countryName = countryName
    }

    init {
        refreshCountriesData()
    }

    val _allCountriesDataLiveData = MutableLiveData<DataResult<List<CountryData>?>>()
    val countryDataLiveData: LiveData<CountryData>? =
        Transformations.map(_allCountriesDataLiveData){ result ->
            when(result){
                is SuccessResult -> {
                    result.data!!.find { it.name == countryName }
                }
                is FailureResult -> null
                is FromCacheResult ->{
                    _popupMessage.value = Event(result.message)
                    result.data!!.find { it.name == countryName }
                }
            }
        }

    val countryExceptionLiveData: LiveData<String> = Transformations.map(_allCountriesDataLiveData){ result ->
        if (result is FailureResult)
            result.exception
        else
            null
    }

    fun refreshCountriesData(){
        viewModelScope.launch {
            _refreshWorldDataLiveData.value = true
            val data = CovidApp.repository.getAllCountriesData()
            _allCountriesDataLiveData.value = data
            _refreshWorldDataLiveData.value = false
        }
    }

    private val allCountriesDataHistoryLiveData: LiveData<List<CountryData>> =
        CovidApp.repository.allCountriesHistoryDataLiveData
    val countryDataHistoryLiveData:LiveData<List<CountryData>> =
        Transformations.map(allCountriesDataHistoryLiveData) { historyData ->
            historyData.filter { it.name == countryName }
        }
}
