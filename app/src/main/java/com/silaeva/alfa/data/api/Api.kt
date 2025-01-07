package com.silaeva.alfa.data.api

import com.silaeva.alfa.data.data_source.BinInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): Response<BinInfo>
}