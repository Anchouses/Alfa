package com.silaeva.alfa.domain.repository

import com.silaeva.alfa.domain.data_model.BinInfoModel

interface InfoRepository {

    suspend fun getBinInfo(bin: String): BinInfoModel
}