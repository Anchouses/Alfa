package com.silaeva.alfa.domain.data_model

data class BinCheckedModel(
    val bin: Int,
    val binInfoChecked: BinInfoCheckedModel
)

data class BinInfoCheckedModel(
    val number: NumberCheckedModel,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryCheckedModel,
    val bank: BankCheckedModel
)

data class NumberCheckedModel(
    val length: Int?,
    val luhn: Boolean?
)

data class CountryCheckedModel(
    val numeric: String?,
    val alpha2: String?,
    val name: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Double?,
    val longitude: Double?
)

data class BankCheckedModel(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)