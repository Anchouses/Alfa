package com.silaeva.alfa.domain.interactor

import com.silaeva.alfa.domain.data_model.BankCheckedModel
import com.silaeva.alfa.domain.data_model.BinCheckedModel
import com.silaeva.alfa.domain.data_model.BinInfoCheckedModel
import com.silaeva.alfa.domain.data_model.BinInfoModel
import com.silaeva.alfa.domain.data_model.CountryCheckedModel
import com.silaeva.alfa.domain.data_model.NumberCheckedModel
import com.silaeva.alfa.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow

class HistoryInteractor(private val historyRepository: HistoryRepository) {

    fun getBinList(): Flow<List<BinCheckedModel>> {
        return historyRepository.getBinList()
    }

    private fun addBin(binCheckedModel: BinCheckedModel) {
        historyRepository.addBin(binCheckedModel)
    }

    fun saveInfo(bin: Int, binInfoModel: BinInfoModel) {
        val binCheckedModel = BinCheckedModel(
            bin = bin,
            binInfoChecked = BinInfoCheckedModel(
                number = NumberCheckedModel(
                    length = binInfoModel.number.length,
                    luhn = binInfoModel.number.luhn
                ),
                scheme = binInfoModel.scheme,
                type = binInfoModel.type,
                brand = binInfoModel.brand,
                prepaid = binInfoModel.prepaid,
                country = CountryCheckedModel(
                    numeric = binInfoModel.country.numeric,
                    alpha2 = binInfoModel.country.alpha2,
                    name = binInfoModel.country.name,
                    emoji = binInfoModel.country.emoji,
                    currency = binInfoModel.country.currency,
                    latitude = binInfoModel.country.latitude,
                    longitude = binInfoModel.country.longitude
                ),
                bank = BankCheckedModel(
                    name = binInfoModel.bank.name,
                    url = binInfoModel.bank.url,
                    phone = binInfoModel.bank.phone,
                    city = binInfoModel.bank.city
                )
            )
        )
        addBin(binCheckedModel)
    }
}