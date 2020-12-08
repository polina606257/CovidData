package com.example.coviddata.ui.worlddata

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.coviddata.model.WorldData
import com.example.coviddata.CovidApp
import com.example.coviddata.datasource.DataResult
import com.example.coviddata.datasource.Repository
import com.example.coviddata.ui.BaseViewModel

class WorldDataViewModel @ViewModelInject constructor(val repository: Repository) : BaseViewModel<WorldData>(){

    val worldDataHistoryLiveData: LiveData<List<WorldData>> = repository.worldDataHistoryLiveData
    override suspend fun getData(): DataResult<WorldData?> {
        return repository.getWorldData()
    }
}


