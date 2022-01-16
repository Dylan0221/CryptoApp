package com.example.cryptoapp.data.repository

import com.example.cryptoapp.data.remote.api_service.CoinGeckoApi
import com.example.cryptoapp.data.remote.dto.CoinItemDto
import com.example.cryptoapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinGeckoApi
): CoinRepository {
    override suspend fun getCoins(): List<CoinItemDto> =
        api.getCoinsList()

    override suspend fun getCoinDetails(coinId: String): List<CoinItemDto> =
        api.getCoin(ids = coinId)



}