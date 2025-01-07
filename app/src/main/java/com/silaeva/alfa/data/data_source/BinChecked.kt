package com.silaeva.alfa.data.data_source

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BinChecked(
    @PrimaryKey
    val bin: Int,
    @Embedded val binInfoChecked: BinInfoChecked
)

data class BinInfoChecked(
    @Embedded val number: NumberChecked,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    @Embedded val country: CountryChecked,
    @Embedded val bank: BankChecked
)

data class NumberChecked(
    val length: Int?,
    val luhn: Boolean?
)

data class CountryChecked(
    val numeric: String?,
    val alpha2: String?,
    val countryName: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Double?,
    val longitude: Double?
)

data class BankChecked(
    val bankName: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)
