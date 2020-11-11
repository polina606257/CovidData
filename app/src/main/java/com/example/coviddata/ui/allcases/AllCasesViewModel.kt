package com.example.coviddata.ui.allcases

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.coviddata.CovidApp

class AllCasesViewModel : ViewModel(){
    init {
        refreshAllCases()
    }
    val allCasesLiveData = CovidApp.repository.allcasesLastLiveData
/*    val casesLiveData = Transformations.map(allCasesLiveData){
        it?.cases.toString()
    }
    val deathsLiveData = Transformations.map(allCasesLiveData){
        it?.deaths.toString()
    }
    val recoveredLiveData = Transformations.map(allCasesLiveData){
        it?.recovered.toString()
    }*/
    fun refreshAllCases(){
        CovidApp.repository.refreshAllCases()
    }
}