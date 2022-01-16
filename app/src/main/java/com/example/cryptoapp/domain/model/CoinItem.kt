package com.example.cryptoapp.domain.model

import com.example.cryptoapp.data.remote.dto.Roi

data class CoinItem (
    val ath: Double,
    val athChangePercentage: Double,
    val circulatingSupply: Double,
    val currentPrice: Double,
    val high24h: Double,
    val id: String,
    val image: String,
    val lastUpdated: String,
    val low24h: Double,
    val marketCap: Double,
    val marketCapChangePercentage24h: Double,
    val marketCapRank: Int,
    val maxSupply: Double,
    val name: String,
    val priceChange24h: Double,
    val priceChangePercentage24h: Double,
    val symbol: String,
    val totalSupply: Double,
    val totalVolume: Double,
    )