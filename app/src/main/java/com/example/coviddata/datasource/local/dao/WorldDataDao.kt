package com.example.covidappapi.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.covidappapi.model.WorldData

@Dao
interface WorldDataDao {

        @Query("SELECT * FROM WorldData")
        fun getHistoryWorldDataLiveData(): LiveData<List<WorldData>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insert(dataWorldData: WorldData)

        @Delete
        fun delete(dataWorldData: WorldData)
    }