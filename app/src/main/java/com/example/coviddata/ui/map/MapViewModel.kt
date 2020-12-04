package com.example.coviddata.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coviddata.CovidApp
import com.example.coviddata.model.CountryData

class MapViewModel : ViewModel() {
    private lateinit var converter: ColorGroupConverter
    private val _downloadMapLiveData = MutableLiveData<Boolean>()
    val downloadMapLiveData: LiveData<Boolean> = _downloadMapLiveData



    fun setDownloadStatus(isLoading: Boolean){
        _downloadMapLiveData.value = isLoading
    }
//    val allCountriesLastLiveData = CovidApp.repository.allCountriesDataLastLiveData

//    init{
//        allCountriesLastLiveData.observeForever{
//            converter = ColorGroupConverter(it)
//        }
//    }

    fun getGroupNumber(country: CountryData): Int = converter.getGroupNumber(country)
}



