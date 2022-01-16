package com.example.cryptoapp.presentation.viewModel.shared_viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel() {

    lateinit var coinId: String

    init {
        Log.d(TAG, "LOG: sharedViewModel has been initialized")
    }

}