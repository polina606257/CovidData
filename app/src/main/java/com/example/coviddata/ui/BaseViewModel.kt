package com.example.coviddata.ui

import androidx.lifecycle.*
import com.example.coviddata.datasource.*
import kotlinx.coroutines.launch

abstract class  BaseViewModel<T> : ViewModel() {
    private val _refreshLiveData = MutableLiveData<Boolean>()
    val refreshLiveData: LiveData<Boolean> = _refreshLiveData
    private val _popupMessage = MutableLiveData<Event<String>>()
    val popupMessage: LiveData<Event<String>> = _popupMessage

    private val _mainLiveData = MutableLiveData<DataResult<T?>>()
    val mainLiveData =
        Transformations.map(_mainLiveData){ result ->
        when(result){
            is SuccessResult -> result.data
            is FailureResult -> null
            is FromCacheResult ->{
                _popupMessage.value = Event(result.message)
                result.data
            }
        }
    }

    val exceptionLiveData =
        Transformations.map(_mainLiveData) { result ->
            if (result is FailureResult)
                result.exception
            else
                null
        }

    fun refreshData(){
        viewModelScope.launch {
            _refreshLiveData.value = true
            _mainLiveData.value = getData()
            _refreshLiveData.value = false
        }
    }

    abstract suspend fun getData(): DataResult<T?>
}