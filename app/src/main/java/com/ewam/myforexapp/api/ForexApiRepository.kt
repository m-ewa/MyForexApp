package com.ewam.myforexapp.api

import com.ewam.myforexapp.models.DataModel
import retrofit2.HttpException
import javax.inject.Inject

class ForexApiRepository @Inject constructor(private val service: ForexApiService) {
    suspend fun getLatestForexCurrencyData(symbols: String): DataModel? {
        val response = service.getLatestForexCurrencyData(
            "262a60748feecf16ea6c2b28c464743a",
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
            "262a60748feecf16ea6c2b28c464743a",
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