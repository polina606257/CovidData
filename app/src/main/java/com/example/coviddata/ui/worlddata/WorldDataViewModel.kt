package com.example.coviddata.ui.worlddata

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.*
import com.example.coviddata.model.WorldData
import com.example.coviddata.CovidApp
import kotlinx.coroutines.launch

class WorldDataViewModel : ViewModel(){
    val _refreshWorldDataLiveData = MutableLiveData<Boolean>()
    val _worldDataLiveData = MutableLiveData<WorldData?>()
    val worldDataLiveData: LiveData<WorldData?> = _worldDataLiveData



    fun refreshWorldData(){
        viewModelScope.launch {
            _refreshWorldDataLiveData.value = true
            val data = CovidApp.repository.getWorldData()
            _worldDataLiveData.value = data
            _refreshWorldDataLiveData.value = false
        }
    }















    val worldDataHistoryLiveData: LiveData<List<WorldData>> = CovidApp.repository.worldDataHistoryLiveData
}


