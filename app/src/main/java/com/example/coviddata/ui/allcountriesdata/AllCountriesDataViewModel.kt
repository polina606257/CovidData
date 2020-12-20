 package com.example.coviddata.ui.allcountriesdata

import androidx.lifecycle.*
import com.example.coviddata.model.CountryData
import com.example.coviddata.CovidApp
import com.example.coviddata.datasource.DataResult
import com.example.coviddata.datasource.Repository
import com.example.coviddata.ui.BaseViewModel
import com.example.coviddata.ui.Event
import com.example.coviddata.ui.worlddata.WorldDataViewModel
import javax.inject.Inject

 enum class SortParamCountries {
    NAME, CASES
}

class AllCountriesDataViewModel @Inject constructor(private val repository: Repository): BaseViewModel<List<CountryData>>() {
    private val _navigateToDetails = MutableLiveData<Event<CountryData>>()
    val navigateToDetails: LiveData<Event<CountryData>> = _navigateToDetails

    init {
        refreshData()
    }

    val sortParamCountriesLiveData: MutableLiveData<SortParamCountries> = MutableLiveData(SortParamCountries.NAME)
    val filterParamLiveData: MutableLiveData<String> = MutableLiveData<String>("")

    val countriesLiveData = MediatorLiveData<List<CountryData>>().apply {
        addSource(sortParamCountriesLiveData) {
            value = prepareListCountries()
        }
        addSource(filterParamLiveData) {
            value = prepareListCountries()
        }
        addSource(mainLiveData) {
            value = prepareListCountries()
        }
    }

    private fun prepareListCountries(): List<CountryData>?{
        val countries = mainLiveData.value
        val filteredCountries = if (filterParamLiveData.value != "")
            countries?.filter { it.name.startsWith(filterParamLiveData.value!!, true)  }
        else
            countries
        return when(sortParamCountriesLiveData.value!!){
            SortParamCountries.NAME -> filteredCountries?.sortedBy { it.name }
            SortParamCountries.CASES -> filteredCountries?.sortedByDescending { it.cases }
        }
    }

    fun showCountryDetails(countryData: CountryData){
        _navigateToDetails.value = Event(countryData)
    }

    override suspend fun getData(): DataResult<List<CountryData>?> {
       return repository.getAllCountriesData()
    }
}

 @Suppress("UNCHECKED_CAST")
 class AllCountriesViewModelFactory @Inject constructor(private val repository: Repository) :
     ViewModelProvider.NewInstanceFactory() {
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
         return AllCountriesDataViewModel(repository) as T
     }
 }
