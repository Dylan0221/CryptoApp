package com.example.cryptoapp.domain.use_cases

import android.content.ContentValues.TAG
import android.util.Log
import com.example.cryptoapp.data.remote.dto.toCoinItem
import com.example.cryptoapp.domain.model.CoinItem
import com.example.cryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<CoinItem> = flow {
        try {
            val coin = repository.getCoinDetails(coinId)[0].toCoinItem()
            Log.d(TAG, "LOG: we have reached coin details by remote data ")
            emit(coin)


        } catch (e: HttpException) {
            Log.d(TAG, "LOG: an http exception with coinDetails remote data has occurred ")
            val errorCoin = CoinItem(
                name = "errorCoin",
                athChangePercentage = 0.toDouble(),
                ath = 0.toDouble(),
                priceChangePercentage24h = 0.toDouble(),
                id = "",
                lastUpdated = "" ,
                priceChange24h = 0.toDouble(),
                currentPrice = 0.toDouble(),
                maxSupply = 0.toDouble(),
                marketCapRank = 0,
                marketCapChangePercentage24h = 0.toDouble(),
                marketCap = 0.toDouble(),
                totalVolume = 0.toDouble(),
                image = "",
                symbol = "",
                totalSupply = 0.toDouble(),
                circulatingSupply = 0.toDouble(),
                low24h = 0.toDouble() ,
                high24h = 0.toDouble()
            )
            emit(errorCoin)

        }
    }
}