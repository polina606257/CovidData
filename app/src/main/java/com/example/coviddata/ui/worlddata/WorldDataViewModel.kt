package com.example.coviddata.ui.worlddata

import androidx.lifecycle.*
import com.example.coviddata.model.WorldData
import com.example.coviddata.CovidApp
import com.example.coviddata.datasource.DataResult
import com.example.coviddata.ui.BaseViewModel

class WorldDataViewModel : BaseViewModel<WorldData>(){

    init {
        refreshData()
    }

    val worldDataHistoryLiveData: LiveData<List<WorldData>> = CovidApp.repository.worldDataHistoryLiveData
    override suspend fun getData(): DataResult<WorldData?> {
        return CovidApp.repository.getWorldData()
    }
}


