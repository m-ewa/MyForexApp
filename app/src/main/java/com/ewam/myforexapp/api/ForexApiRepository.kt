package com.ewam.myforexapp.api

import com.ewam.myforexapp.models.DataModel
import retrofit2.HttpException
import javax.inject.Inject

class ForexApiRepository @Inject constructor(private val service: ForexApiService) {
    suspend fun getLatestForexCurrencyData(symbols: String): DataModel? {
        val response = service.getLatestForexCurrencyData(
            "295d816db28ef95259861991cec1e59d",
            symbols,
            "1"
        )
        if (response.isSuccessful) {
            return response.body()
        } else {
            throw HttpException(response)
        }
    }
    suspend fun getForexCurrencyDataByDate(date: String, symbols: String): DataModel? {
        val response = service.getForexCurrencyDataByDate(
            date,
            "295d816db28ef95259861991cec1e59d",
            symbols,
            "1"
        )
        if (response.isSuccessful) {
            return response.body()
        } else {
            throw HttpException(response)
        }
    }
}