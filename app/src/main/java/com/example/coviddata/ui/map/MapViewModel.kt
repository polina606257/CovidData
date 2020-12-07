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

enum class SortParamMap {
    CASES, DEATHS, RECOVERED, CASESPERMILLION, DEATHSPERMILLION, TESTPERMILLION
}

class MapViewModel : BaseViewModel<List<CountryData>>() {
    lateinit var converter: ColorGroupConverter
//    private val _downloadMapLiveData = MutableLiveData<Boolean>()
//    val downloadMapLiveData: LiveData<Boolean> = _downloadMapLiveData




    val sortParamLiveData: MutableLiveData<SortParamMap> = MutableLiveData(SortParamMap.CASES)

    val countriesLiveData = MediatorLiveData<List<CountryData>>().apply {
        addSource(sortParamLiveData) {
            value = prepareListCountries()
        }
        addSource(mainLiveData) {
            value = prepareListCountries()
        }
    }

    private fun prepareListCountries(): List<CountryData>?{
        val countries = mainLiveData.value
        return when(sortParamLiveData.value!!){
            SortParamMap.CASES -> countries?.sortedBy { it.cases }
            SortParamMap.DEATHS -> countries?.sortedBy { it.deaths }
            SortParamMap.RECOVERED -> countries?.sortedBy { it.recovered }
            SortParamMap.CASESPERMILLION -> countries?.sortedBy { it.casesPerOneMillion }
            SortParamMap.DEATHSPERMILLION -> countries?.sortedBy { it.deathsPerOneMillion }
            SortParamMap.TESTPERMILLION -> countries?.sortedBy { it.testsPerOneMillion }
        }
    }


//    fun setDownloadStatus(isLoading: Boolean){
//        _downloadMapLiveData.value = true
//    }

    init{
        countriesLiveData.observeForever{
            it?.let{
                converter = ColorGroupConverter(it)
            }
        }
    }

    fun getMarkerId(country: CountryData): Int = converter.getMarkerId(country)

    override suspend fun getData(): DataResult<List<CountryData>?> {
         return CovidApp.repository.getAllCountriesData()
    }

}



