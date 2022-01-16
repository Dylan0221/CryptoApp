package com.example.cryptoapp.data.remote.dto

import com.example.cryptoapp.domain.model.CoinItem
import com.google.gson.annotations.SerializedName

data class CoinItemDto(
    val ath: Double,
    @SerializedName("ath_change_percentage")
    val athChangePercentage: Double,
    @SerializedName("ath_date")
    val athDate: String,
    val atl: Double,
    @SerializedName("atl_change_percentage")
    val atlChangePercentage: Double,
    @SerializedName("atl_date")
    val atlDate: String,
    @SerializedName("circulating_supply")
    val circulatingSupply: Double,
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("fully_diluted_valuation")
    val fullyDilutedValuation: Long,
    @SerializedName("high_24h")
    val high24h: Double,
    val id: String,
    val image: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("low_24h")
    val low24h: Double,
    @SerializedName("market_cap")
    val marketCap: Double,
    @SerializedName("market_cap_change_24h")
    val marketCapChange24h: Double,
    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Double,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    @SerializedName("max_supply")
    val maxSupply: Double,
    val name: String,
    @SerializedName("price_change_24h")
    val priceChange24h: Double,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double,
    val roi: Roi,
    val symbol: String,
    @SerializedName("total_supply")
    val totalSupply: Double,
    @SerializedName("total_volume")
    val totalVolume: Double
)
fun CoinItemDto.toCoinItem(): CoinItem{
    return CoinItem(
        ath = ath,
        athChangePercentage = athChangePercentage,
        circulatingSupply = circulatingSupply,
        currentPrice = currentPrice,
        high24h = high24h,
        id = id,
        image = image,
        lastUpdated = lastUpdated,
        low24h = low24h,
        marketCap = marketCap,
        marketCapChangePercentage24h = marketCapChangePercentage24h,
        marketCapRank = marketCapRank,
        maxSupply = maxSupply,
        name = name,
        priceChange24h = priceChange24h,
        priceChangePercentage24h = priceChangePercentage24h,
        symbol = symbol,
        totalSupply = totalSupply,
        totalVolume = totalVolume
    )
}
