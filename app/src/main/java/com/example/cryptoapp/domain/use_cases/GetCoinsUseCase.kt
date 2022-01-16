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

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(): Flow<List<CoinItem>> = flow {
        try {
            val coins = repository.getCoins().map {
                it.toCoinItem()

            }
            Log.d(TAG, "LOG: we have reached list of coins by remote data")

            emit(coins)

        } catch (e: HttpException){

            Log.d(TAG, "LOG: we have reached list of coin 1")
            emit(emptyList())
        }


    }




}