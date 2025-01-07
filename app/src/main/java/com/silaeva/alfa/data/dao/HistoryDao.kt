package com.silaeva.alfa.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.silaeva.alfa.data.data_source.BinChecked
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query("SELECT * FROM BinChecked")
    fun getBinList(): Flow<List<BinChecked>>

    @Insert
    fun addBin(binChecked: BinChecked)
}