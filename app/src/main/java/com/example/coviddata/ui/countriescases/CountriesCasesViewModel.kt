package com.example.coviddata.ui.countriescases

import android.widget.SearchView
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.coviddata.CovidApp

class CountriesCasesViewModel : ViewModel() {

    var sortParam: CountriesCasesFragment.SortParam? = null

    fun initSortParam(sortParam: CountriesCasesFragment.SortParam){
        this.sortParam = sortParam
    }

    private val countriesLiveData = CovidApp.repository.countriesLiveData
    val countryLiveData = Transformations.map(countriesLiveData){ countries ->
        countries.sortedBy { sortParam } // Enum - нужна переменная
    }

    fun getCountries() {
        CovidApp.repository.refreshCountriesCases()
    }
}
