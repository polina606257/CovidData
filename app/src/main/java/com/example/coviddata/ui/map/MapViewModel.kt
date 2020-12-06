package com.example.coviddata.ui.map

import androidx.lifecycle.*
import com.example.coviddata.CovidApp
import com.example.coviddata.datasource.DataResult
import com.example.coviddata.datasource.FailureResult
import com.example.coviddata.datasource.FromCacheResult
import com.example.coviddata.datasource.SuccessResult
import com.example.coviddata.model.CountryData
import com.example.coviddata.ui.BaseViewModel
import com.example.coviddata.ui.Event
import com.example.coviddata.ui.allcountriesdata.SortParamCountries
import kotlinx.coroutines.launch

enum class SortParamMap {
    CASES, DEATHS, RECOVERED, CASESPERMILLION, DEATHSPERMILLION, TESTPERMILLION
}

class MapViewModel : BaseViewModel() {
    lateinit var converter: ColorGroupConverter
    var titleNum: Int? = 0
//    var title: String? = null
//    private val _downloadMapLiveData = MutableLiveData<Boolean>()
//    val downloadMapLiveData: LiveData<Boolean> = _downloadMapLiveData


    init {
        refreshCountriesData()
    }

    val _allCountriesDataLiveData = MutableLiveData<DataResult<List<CountryData>?>>()
    val allCountriesLastDataLiveData: LiveData<List<CountryData>?> =
        Transformations.map(_allCountriesDataLiveData){ result ->
            when(result){
                is SuccessResult -> result.data?.filter {
                    it.date == (result.data.maxByOrNull { it.date })?.date.toString() }
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

    val sortParamLiveData: MutableLiveData<SortParamMap> = MutableLiveData(SortParamMap.CASES)

    val countriesLiveData = MediatorLiveData<List<CountryData>>().apply {
        addSource(sortParamLiveData) {
            value = prepareListCountries()
        }
        addSource(allCountriesLastDataLiveData) {
            value = prepareListCountries()
        }
    }

    private fun prepareListCountries(): List<CountryData>?{
        val countries = allCountriesLastDataLiveData.value
        return when(sortParamLiveData.value!!){
            SortParamMap.CASES -> countries?.sortedBy { it.cases }
            SortParamMap.DEATHS -> countries?.sortedBy { it.deaths }
            SortParamMap.RECOVERED -> countries?.sortedBy { it.recovered }
            SortParamMap.CASESPERMILLION -> countries?.sortedBy { it.casesPerOneMillion }
            SortParamMap.DEATHSPERMILLION -> countries?.sortedBy { it.deathsPerOneMillion }
            SortParamMap.TESTPERMILLION -> countries?.sortedBy { it.testsPerOneMillion }
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


//    fun setDownloadStatus(isLoading: Boolean){
//        _downloadMapLiveData.value = true
//    }

    init{
        countriesLiveData.observeForever{
            converter = ColorGroupConverter(it)
        }
    }

    fun getGroupNumber(country: CountryData): Int = converter.getGroupNumber(country)

}



