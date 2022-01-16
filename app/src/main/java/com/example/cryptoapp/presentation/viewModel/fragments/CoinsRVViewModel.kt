package com.example.cryptoapp.presentation.viewModel.fragments

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.domain.model.CoinItem
import com.example.cryptoapp.domain.use_cases.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsRVViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {

    var coinList: List<CoinItem> = emptyList()

    init {
        getCoins()
        Log.d(TAG, "LOG: We have reached the init of rvViewmodel ")
    }


    private fun getCoins(){
        viewModelScope.launch {
                getCoinsUseCase().collect { value ->
                    coinList = value
            }
        }
    }
}
