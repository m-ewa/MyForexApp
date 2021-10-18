package com.ewam.myforexapp.api

import com.ewam.myforexapp.models.DataModel
import retrofit2.HttpException
import javax.inject.Inject

class ForexApiRepository @Inject constructor(private val service: ForexApiService) {
    suspend fun getLatestForexCurrencyData(symbols: String): DataModel? {
        val response = service.getLatestForexCurrencyData(
            "9abb91b2a12547fb4374bd9c7746844e",
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
            "9abb91b2a12547fb4374bd9c7746844e",
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
