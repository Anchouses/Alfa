package com.silaeva.alfa.data.repository_impl

import android.content.Context
import androidx.room.Room
import com.silaeva.alfa.data.dao.HistoryDatabase
import com.silaeva.alfa.data.data_source.BankChecked
import com.silaeva.alfa.data.data_source.BinChecked
import com.silaeva.alfa.data.data_source.BinInfoChecked
import com.silaeva.alfa.data.data_source.CountryChecked
import com.silaeva.alfa.data.data_source.NumberChecked
import com.silaeva.alfa.domain.data_model.BankCheckedModel
import com.silaeva.alfa.domain.data_model.BinCheckedModel
import com.silaeva.alfa.domain.data_model.BinInfoCheckedModel
import com.silaeva.alfa.domain.data_model.CountryCheckedModel
import com.silaeva.alfa.domain.data_model.NumberCheckedModel
import com.silaeva.alfa.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executor
import java.util.concurrent.Executors

const val DATABASE_NAME = "BinHistory"

class HistoryRepositoryImpl private constructor(context: Context): HistoryRepository {

    private val database: HistoryDatabase = Room.databaseBuilder(
        context.applicationContext,
        HistoryDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val historyDao = database.historyDao()

    private val executor: Executor = Executors.newSingleThreadScheduledExecutor()

    override fun getBinList(): Flow<List<BinCheckedModel>> {
        val list: Flow<List<BinChecked>> = historyDao.getBinList()
        return list.map {item ->
            item.map {
                BinCheckedModel(
                    bin = it.bin,
                    binInfoChecked = BinInfoCheckedModel(
                        number = NumberCheckedModel(
                            length = it.binInfoChecked.number?.length,
                            luhn = it.binInfoChecked.number?.luhn
                        ),
                        scheme = it.binInfoChecked.scheme,
                        type = it.binInfoChecked.type,
                        brand = it.binInfoChecked.brand,
                        prepaid = it.binInfoChecked.prepaid,
                        country = CountryCheckedModel(
                            numeric = it.binInfoChecked.country?.numeric,
                            alpha2 = it.binInfoChecked.country?.alpha2,
                            name = it.binInfoChecked.country?.countryName,
                            emoji = it.binInfoChecked.country?.emoji,
                            currency = it.binInfoChecked.country?.currency,
                            latitude = it.binInfoChecked.country?.latitude,
                            longitude = it.binInfoChecked.country?.longitude
                        ),
                        bank = BankCheckedModel(
                            name = it.binInfoChecked.bank?.bankName,
                            url = it.binInfoChecked.bank?.url,
                            phone = it.binInfoChecked.bank?.phone,
                            city = it.binInfoChecked.bank?.city
                        )
                    )
                )
            }
        }
    }

    override fun addBin(binCheckedModel: BinCheckedModel) {
        val binChecked = BinChecked(
            bin = binCheckedModel.bin,
            binInfoChecked = BinInfoChecked(
                number = NumberChecked(
                    length = binCheckedModel.binInfoChecked.number?.length,
                    luhn = binCheckedModel.binInfoChecked.number?.luhn
                ),
                scheme = binCheckedModel.binInfoChecked.scheme,
                type = binCheckedModel.binInfoChecked.type,
                brand = binCheckedModel.binInfoChecked.brand,
                prepaid = binCheckedModel.binInfoChecked.prepaid,
                country = CountryChecked(
                    numeric = binCheckedModel.binInfoChecked.country?.numeric,
                    alpha2 = binCheckedModel.binInfoChecked.country?.alpha2,
                    countryName = binCheckedModel.binInfoChecked.country?.name,
                    emoji = binCheckedModel.binInfoChecked.country?.emoji,
                    currency = binCheckedModel.binInfoChecked.country?.currency,
                    latitude = binCheckedModel.binInfoChecked.country?.latitude,
                    longitude = binCheckedModel.binInfoChecked.country?.longitude
                ),
                bank = BankChecked(
                    bankName = binCheckedModel.binInfoChecked.bank?.name,
                    url = binCheckedModel.binInfoChecked.bank?.url,
                    phone = binCheckedModel.binInfoChecked.bank?.phone,
                    city = binCheckedModel.binInfoChecked.bank?.city
                )
            )
        )
        executor.execute {
            historyDao.addBin(binChecked)
        }
    }

    companion object {
        private var INSTANCE: HistoryRepositoryImpl? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) INSTANCE = HistoryRepositoryImpl(context)
        }

        fun get(): HistoryRepositoryImpl {
            return INSTANCE ?: throw IllegalStateException("where is history repository?")
        }
    }
}