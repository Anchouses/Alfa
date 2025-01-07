package com.silaeva.alfa.data.repository_impl

import android.content.Context
import com.silaeva.alfa.data.api.Api
import com.silaeva.alfa.domain.data_model.BankModel
import com.silaeva.alfa.domain.data_model.BinInfoModel
import com.silaeva.alfa.domain.data_model.CountryModel
import com.silaeva.alfa.domain.data_model.NumberModel
import com.silaeva.alfa.domain.repository.InfoRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InfoRepositoryImpl private constructor(context: Context) : InfoRepository {

    private val interceptor = HttpLoggingInterceptor()

    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://lookup.binlist.net/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: Api = retrofit.create(Api::class.java)

    override suspend fun getBinInfo(bin: String): BinInfoModel {
        val response = api.getBinInfo(bin)

        return try {
            if (response.isSuccessful) {
                response.body()?.let {
                    BinInfoModel(
                        number = NumberModel(
                            length = it.number?.length,
                            luhn = it.number?.luhn
                        ),
                        scheme = it.scheme,
                        type = it.type,
                        brand = it.brand,
                        prepaid = it.prepaid,
                        country = CountryModel(
                            numeric = it.country?.numeric,
                            alpha2 = it.country?.alpha2,
                            name = it.country?.name,
                            emoji = it.country?.emoji,
                            currency = it.country?.currency,
                            latitude = it.country?.latitude,
                            longitude = it.country?.longitude
                        ),
                        bank = BankModel(
                            name = it.bank?.name,
                            url = it.bank?.url,
                            phone = it.bank?.phone,
                            city = it.bank?.city
                        )
                    )
                } ?: throw Exception("Response body is null")
            } else {
                throw Exception("Error: ${response.code()}")

            }
        } catch (e: Exception) {
            BinInfoModel(
                number = NumberModel(
                    length = null,
                    luhn = false
                ),
                scheme = null,
                type = null,
                brand = null,
                prepaid = null,
                country = CountryModel(
                    numeric = null,
                    alpha2 = null,
                    name = null,
                    emoji = null,
                    currency = null,
                    latitude = null,
                    longitude = null
                ),
                bank = BankModel(
                    name = null,
                    url = null,
                    phone = null,
                    city = null
                )
            )
        }
    }

    companion object {
        private var INSTANCE: InfoRepositoryImpl? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) INSTANCE = InfoRepositoryImpl(context)
        }

        fun get(): InfoRepositoryImpl {
            return INSTANCE ?: throw IllegalStateException("where is history repository?")
        }
    }
}