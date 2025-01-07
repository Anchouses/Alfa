package com.silaeva.alfa.domain.interactor

import com.silaeva.alfa.domain.data_model.BinInfoModel
import com.silaeva.alfa.domain.repository.InfoRepository

class InfoInteractor (private val infoRepository: InfoRepository) {

    suspend fun getBinInfo(bin: String): BinInfoModel {
        return infoRepository.getBinInfo(bin)
    }
}