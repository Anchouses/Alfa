package com.silaeva.alfa.domain.repository

import com.silaeva.alfa.domain.data_model.BinCheckedModel
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun getBinList(): Flow<List<BinCheckedModel>>

    fun addBin(binCheckedModel: BinCheckedModel)
}