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
import kotlinx.coroutines.launch

class MapViewModel : BaseViewModel() {
    private lateinit var converter: ColorGroupConverter
    private val _downloadMapLiveData = MutableLiveData<Boolean>()
    val downloadMapLiveData: LiveData<Boolean> = _downloadMapLiveData


    init {
        refreshCountriesData()
    }

    val _allCountriesDataLiveData = MutableLiveData<DataResult<List<CountryData>?>>()
    val allCountriesLastDataLiveData: LiveData<List<CountryData>> =
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

    fun refreshCountriesData(){
        viewModelScope.launch {
            _refreshWorldDataLiveData.value = true
            val data = CovidApp.repository.getAllCountriesData()
            _allCountriesDataLiveData.value = data
            _refreshWorldDataLiveData.value = false
        }
    }


    fun setDownloadStatus(isLoading: Boolean){
        _downloadMapLiveData.value = true
    }

    init{
        allCountriesLastDataLiveData.observeForever{
            converter = ColorGroupConverter(it)
        }
    }

    fun getGroupNumber(country: CountryData): Int = converter.getGroupNumber(country)
}



