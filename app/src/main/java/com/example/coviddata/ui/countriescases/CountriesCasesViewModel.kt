package com.example.coviddata.ui.countriescases

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.coviddata.CovidApp

class CountriesCasesViewModel : ViewModel(){
    val countriesLiveData = Transformations.map(CovidApp.repository.countriesLiveData){countries->
        countries.sortedBy { it.cases }
    }
}