 package com.example.coviddata.ui.allcountriesdata

import androidx.lifecycle.*
import com.example.coviddata.model.CountryData
import com.example.coviddata.CovidApp
import com.example.coviddata.datasource.DataResult
import com.example.coviddata.datasource.FailureResult
import com.example.coviddata.datasource.FromCacheResult
import com.example.coviddata.datasource.SuccessResult
import com.example.coviddata.ui.BaseViewModel
import com.example.coviddata.ui.Event
import kotlinx.coroutines.launch

 enum class SortParam {
    NAME, CASES
}

class AllCountriesDataViewModel : BaseViewModel() {
    private val _navigateToDetails = MutableLiveData<Event<CountryData>>()
    val navigateToDetails: LiveData<Event<CountryData>> = _navigateToDetails

    init {
        refreshCountriesData()
    }

    val _allCountriesDataLiveData = MutableLiveData<DataResult<List<CountryData>?>>()
    val allCountriesDataLiveData: LiveData<List<CountryData>?> =
        Transformations.map(_allCountriesDataLiveData){ result ->
        when(result){
            is SuccessResult -> result.data
            is FailureResult -> null
            is FromCacheResult ->{
                _popupMessage.value = Event(result.message)
                result.data
            }
        }
    }

    val allCountriesExceptionLiveData: LiveData<String> = Transformations.map(_allCountriesDataLiveData){ result ->
        if (result is FailureResult)
            result.exception
        else
            null
    }

    val sortParamLiveData: MutableLiveData<SortParam> = MutableLiveData(SortParam.NAME)
    val filterParamLiveData: MutableLiveData<String> = MutableLiveData<String>("")

    val countriesLiveData = MediatorLiveData<List<CountryData>>().apply {
        addSource(sortParamLiveData) {
            value = prepareListCountries()
        }
        addSource(filterParamLiveData) {
            value = prepareListCountries()
        }
        addSource(allCountriesDataLiveData) {
            value = prepareListCountries()
        }
    }

    private fun prepareListCountries(): List<CountryData>?{
        val countries = allCountriesDataLiveData.value
        val filteredCountries = if (filterParamLiveData.value != "")
            countries?.filter { it.name.startsWith(filterParamLiveData.value!!, true)  }
        else
            countries
        return when(sortParamLiveData.value!!){
            SortParam.NAME -> filteredCountries?.sortedBy { it.name }
            SortParam.CASES -> filteredCountries?.sortedByDescending { it.cases }
        }
    }

     fun refreshCountriesData(){
        viewModelScope.launch {
            _refreshWorldDataLiveData.value = true
            val data = CovidApp.repository.getAllCountriesData()
            _allCountriesDataLiveData.value = data
            _refreshWorldDataLiveData.value = false
        }
    }

    fun showCountryDetails(countryData: CountryData){
        _navigateToDetails.value = Event(countryData)
    }
}

