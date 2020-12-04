package com.example.coviddata.ui

import androidx.lifecycle.*
import com.example.coviddata.R
import com.example.coviddata.datasource.*

open class  BaseViewModel: ViewModel() {
    val _refreshWorldDataLiveData = MutableLiveData<Boolean>()
    open val refreshWorldDataLiveData: LiveData<Boolean> = _refreshWorldDataLiveData
    val _popupMessage = MutableLiveData<Event<String>>()
    open val popupMessage: LiveData<Event<String>> = _popupMessage

//    open fun getDataLiveData(dataLiveData: MutableLiveData<DataResult<R?>>): LiveData<R?> =
//        Transformations.map(dataLiveData){ result ->
//        when(result){
//            is SuccessResult -> result.data
//            is FailureResult -> null
//            is FromCacheResult ->{
//                _popupMessage.value = Event(result.message)
//                result.data
//            }
//        }
//    }

//    open fun getExceptionLiveData  (dataResultLiveData: MutableLiveData<DataResult<out R?>>): LiveData<String> =
//        Transformations.map(dataResultLiveData) { result ->
//            if (result is FailureResult)
//                result.exception
//            else
//                null
//        }

//    open fun refreshData(dataFromRepository: DataResult<WorldData?>, dataResultLiveData: MutableLiveData<DataResult<WorldData?>>){
//        viewModelScope.launch {
//            refreshWorldDataLiveData.value = true
//            dataResultLiveData.value = dataFromRepository
//            refreshWorldDataLiveData.value = false
//        }
//    }
}