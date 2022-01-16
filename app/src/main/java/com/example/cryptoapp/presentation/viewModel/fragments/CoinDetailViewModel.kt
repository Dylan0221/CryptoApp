package com.example.cryptoapp.presentation.viewModel.fragments

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.domain.model.CoinItem
import com.example.cryptoapp.domain.use_cases.GetCoinDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailsUseCase: GetCoinDetailsUseCase
): ViewModel() {

    var coinId: String = ""

    var coinDetail: CoinItem? = null

    init {
        viewModelScope.launch {
            while (true) {
                if (coinId != "") {
                    getCoinDetails(coinId)
                    break
                }else {
                    delay(1000)
                }
            }

        }

    }




    private suspend fun getCoinDetails(coinId: String) {
        getCoinDetailsUseCase(coinId).collect { value ->
            coinDetail = value
        }
    }
}


