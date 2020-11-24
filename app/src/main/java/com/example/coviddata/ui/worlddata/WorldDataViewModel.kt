package com.example.coviddata.ui.worlddata

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.coviddata.model.WorldData
import com.example.coviddata.CovidApp
import com.example.coviddata.datasource.DataResult
import com.example.coviddata.datasource.FailureResult
import com.example.coviddata.datasource.SuccessResult

class WorldDataViewModel : ViewModel(){

    init {
        refreshAllCases()
    }

    private val _worldLiveData: LiveData<DataResult<WorldData>> = CovidApp.repository.worldDataLastLiveData

    val worldDataLiveData: LiveData<WorldData?> = Transformations.map(_worldLiveData){ result ->
        if (result is SuccessResult)
            result.data
        else
            null
    }
//    var valueData = worldDataLiveData.value

    val worldExceptionLiveData = Transformations.map(_worldLiveData){ result ->
        if (result is FailureResult)
            result.exception
        else
            null
    }
//    var valueException = worldExceptionLiveData.value

    val worldDataHistoryLiveData: LiveData<List<WorldData>> = CovidApp.repository.worldDataHistoryLiveData

    fun refreshAllCases(){
        CovidApp.repository.refreshWorldData()
    }
}