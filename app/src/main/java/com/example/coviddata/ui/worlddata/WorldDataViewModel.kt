package com.example.coviddata.ui.worlddata

import androidx.lifecycle.*
import com.example.coviddata.model.WorldData
import com.example.coviddata.CovidApp
import com.example.coviddata.datasource.DataResult
import com.example.coviddata.datasource.FailureResult
import com.example.coviddata.datasource.FromCacheResult
import com.example.coviddata.datasource.SuccessResult
import com.example.coviddata.ui.Event
import kotlinx.coroutines.launch

class WorldDataViewModel : ViewModel(){
    val refreshWorldDataLiveData = MutableLiveData<Boolean>()
    private val _popupMessage = MutableLiveData<Event<String>>()
    val popupMessage: LiveData<Event<String>> = _popupMessage

    val _worldDataLiveData = MutableLiveData<DataResult<WorldData?>>()
    val worldDataLiveData: LiveData<WorldData?> = Transformations.map(_worldDataLiveData){ result ->
        when(result){
            is SuccessResult -> result.data
            is FailureResult -> null
            is FromCacheResult ->{
                _popupMessage.value = Event(result.message)
                result.data
            }
        }
    }

    val worldExceptionLiveData: LiveData<String> = Transformations.map(_worldDataLiveData){ result ->
        if (result is FailureResult)
            result.exception
        else
            null
    }

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


