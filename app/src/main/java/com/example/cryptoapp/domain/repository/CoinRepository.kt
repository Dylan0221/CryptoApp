package com.example.cryptoapp.domain.repository

import com.example.cryptoapp.data.remote.dto.CoinItemDto
import retrofit2.Response

interface CoinRepository {

    suspend fun getCoins(): List<CoinItemDto>

    suspend fun getCoinDetails(coinId: String): List<CoinItemDto>
}