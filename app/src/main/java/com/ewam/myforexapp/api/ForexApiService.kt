package com.ewam.myforexapp.api

import com.ewam.myforexapp.models.DataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ForexApiService {
    @GET("latest")
    suspend fun getLatestForexCurrencyData(
        @Query("access_key") access_key: String,
        @Query("symbols") symbols: String,
        @Query("format") format: String
    ): Response<DataModel>

    @GET("{date}")
    suspend fun getForexCurrencyDataByDate(
        @Path("date") date: String,
        @Query("access_key") access_key: String,
        @Query("symbols") symbols: String,
        @Query("format") format: String
    ): Response<DataModel>
}
