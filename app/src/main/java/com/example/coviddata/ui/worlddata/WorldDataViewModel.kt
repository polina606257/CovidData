package com.example.coviddata.ui.worlddata

import androidx.lifecycle.*
import com.example.coviddata.model.WorldData
import com.example.coviddata.CovidApp
import com.example.coviddata.datasource.DataResult
import com.example.coviddata.datasource.Repository
import com.example.coviddata.ui.BaseViewModel
import javax.inject.Inject

class WorldDataViewModel @Inject constructor(private val repository: Repository): BaseViewModel<WorldData>(){
    init {
        refreshData()
    }
    val worldDataHistoryLiveData: LiveData<List<WorldData>> = repository.worldDataHistoryLiveData
    override suspend fun getData(): DataResult<WorldData?> {
        return repository.getWorldData()
    }
}

@Suppress("UNCHECKED_CAST")
class WorldViewModelFactory @Inject constructor(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WorldDataViewModel(repository) as T
    }
}


