package com.example.coviddata.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.coviddata.model.WorldData

@Dao
interface WorldDataDao {

    @Query("SELECT * FROM WorldData")
    fun getHistoryWorldDataLiveData(): LiveData<List<WorldData>>

    @Query("SELECT * FROM WorldData")
    suspend fun getHistoryWorldData(): List<WorldData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dataWorldData: WorldData)

    @Delete
    suspend fun delete(dataWorldData: WorldData)

    suspend fun getLastWorldData() = getHistoryWorldData().maxByOrNull { it.date }

}