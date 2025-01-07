package com.silaeva.alfa.data.data_source

data class BinInfo(
    val number: Number? = null,
    val scheme: String? = "",
    val type: String? = "",
    val brand: String? = "",
    val prepaid: Boolean? = false,
    val country: Country? = null,
    val bank: Bank? = null
)

data class Number(
    val length: Int? = 0,
    val luhn: Boolean? = false
)

data class Country(
    val numeric: String? = "",
    val alpha2: String? = "",
    val name: String? = "",
    val emoji: String? = "",
    val currency: String? = "",
    val latitude: Double? = .0,
    val longitude: Double? = .0
)

data class Bank(
    val name: String? = "",
    val url: String? = "",
    val phone: String? = "",
    val city: String? = ""
)