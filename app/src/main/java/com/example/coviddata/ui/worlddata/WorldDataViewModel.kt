package com.example.coviddata.ui.worlddata

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coviddata.model.WorldData
import com.example.coviddata.CovidApp

class WorldDataViewModel : ViewModel(){

    init {
        refreshAllCases()
    }

    private val _allCasesLiveData = CovidApp.repository.worldDataLastLiveData
    val worldDataLiveData: LiveData<WorldData?> = _allCasesLiveData
    val worldDataHistoryLiveData: LiveData<List<WorldData>> = CovidApp.repository.worldDataHistoryLiveData

    fun refreshAllCases(){
        CovidApp.repository.refreshWorldData()
    }
}