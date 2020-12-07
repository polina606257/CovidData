package com.example.coviddata.ui.worlddata

import androidx.lifecycle.*
import com.example.coviddata.model.WorldData
import com.example.coviddata.CovidApp
import com.example.coviddata.datasource.DataResult
import com.example.coviddata.datasource.FailureResult
import com.example.coviddata.datasource.FromCacheResult
import com.example.coviddata.datasource.SuccessResult
import com.example.coviddata.ui.BaseViewModel
import com.example.coviddata.ui.Event
import kotlinx.coroutines.launch

class WorldDataViewModel : BaseViewModel<WorldData>(){

    val worldDataHistoryLiveData: LiveData<List<WorldData>> = CovidApp.repository.worldDataHistoryLiveData
    override suspend fun getData(): DataResult<WorldData?> {
        return CovidApp.repository.getWorldData()
    }
}


