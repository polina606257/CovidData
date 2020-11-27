package com.example.coviddata.ui.worlddata

import androidx.lifecycle.*
import com.example.coviddata.model.WorldData
import com.example.coviddata.CovidApp
import kotlinx.coroutines.launch

class WorldDataViewModel : ViewModel(){
    val refreshWorldDataLiveData = MutableLiveData<Boolean>()
    val _worldDataLiveData = MutableLiveData<WorldData?>()
    val worldDataLiveData: LiveData<WorldData?> = _worldDataLiveData

    init {
        refreshWorldData()
    }

    fun refreshWorldData(){
        viewModelScope.launch {
            refreshWorldDataLiveData.value = true
            val data = CovidApp.repository.getWorldData()
            _worldDataLiveData.value = data
            refreshWorldDataLiveData.value = false
        }
    }















    val worldDataHistoryLiveData: LiveData<List<WorldData>> = CovidApp.repository.worldDataHistoryLiveData
}


