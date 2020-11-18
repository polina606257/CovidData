package com.example.coviddata.ui.allcases

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.covidappapi.model.WorldData
import com.example.coviddata.CovidApp

class AllCasesViewModel : ViewModel(){

    init {
        refreshAllCases()
    }

    private val _allCasesLiveData = CovidApp.repository.allcasesLastLiveData
    val worldDataLiveData: LiveData<WorldData?> = _allCasesLiveData
    val worldDataHistoryLiveData: LiveData<List<WorldData>> = CovidApp.repository.worldDataHistoryLiveData

    fun refreshAllCases(){
        CovidApp.repository.refreshAllCases()
    }
}