package com.silaeva.alfa.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.silaeva.alfa.data.data_source.BinChecked


@Database(entities = [BinChecked::class], version = 1)
abstract class HistoryDatabase: RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}