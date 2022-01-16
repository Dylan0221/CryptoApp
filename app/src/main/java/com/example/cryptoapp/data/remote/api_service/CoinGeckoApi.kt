package com.example.cryptoapp.data.remote.api_service

import com.example.cryptoapp.data.remote.dto.CoinItemDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {

    @GET("coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false")
    suspend fun getCoinsList(): List<CoinItemDto>

    @GET("coins/markets")
    suspend fun getCoin(@Query("vs_currency") vs_currency: String = "usd", @Query("ids") ids: String): List<CoinItemDto>
}