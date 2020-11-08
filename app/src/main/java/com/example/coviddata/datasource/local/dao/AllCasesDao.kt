package com.example.covidappapi.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.covidappapi.model.AllCases

@Dao
interface AllCasesDao {

        @Query("SELECT * FROM AllCases")
        fun getAllCasesLiveData(): LiveData<List<AllCases>>

        @Insert
        fun insert(dataAllCases: AllCases)

        @Delete
        fun delete(dataAllCases: AllCases)
    }