package com.example.coviddata.ui.worlddata

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.*
import com.example.coviddata.model.WorldData
import com.example.coviddata.CovidApp

class WorldDataViewModel : ViewModel(){
    private val _refreshWorldDataLiveData = CovidApp.repository.refreshWorldDataLiveData
    val refreshWorldDataLiveData: LiveData<Boolean> = _refreshWorldDataLiveData

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


