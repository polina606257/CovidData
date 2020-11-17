package com.example.coviddata.ui.allcases

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.covidappapi.model.AllCases
import com.example.coviddata.CovidApp

class AllCasesViewModel : ViewModel(){

    init {
        refreshAllCases()
    }

    private val _allCasesLiveData = CovidApp.repository.allcasesLastLiveData
    val allCasesLiveData: LiveData<AllCases?> = _allCasesLiveData
    val allCasesHistoryLiveData: LiveData<List<AllCases>> = CovidApp.repository.allCasesHistoryLiveData

    fun refreshAllCases(){
        CovidApp.repository.refreshAllCases()
    }
}