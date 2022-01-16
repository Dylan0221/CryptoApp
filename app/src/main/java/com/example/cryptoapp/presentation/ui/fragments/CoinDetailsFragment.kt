package com.example.cryptoapp.presentation.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.FragmentCoinDetailsBinding
import com.example.cryptoapp.databinding.FragmentCoinsRvBinding
import com.example.cryptoapp.presentation.viewModel.fragments.CoinDetailViewModel
import com.example.cryptoapp.presentation.viewModel.shared_viewmodel.SharedViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.math.log

@AndroidEntryPoint
class CoinDetailsFragment : Fragment() {

    private var _binding: FragmentCoinDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CoinDetailViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.coinId = sharedViewModel.coinId
        Log.d(TAG, "LOG: Coin is ${viewModel.coinId} ")


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCoinDetailsBinding.inflate(inflater, container, false)


        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCoinDetails()
    }


    private fun initCoinDetails() {
        Log.d(TAG, "LOG: initCoinDetails function has been initialized")
            lifecycleScope.launch {
                var loading = true
                while (loading) {
                    val coinDetail = viewModel.coinDetail
                    if (coinDetail == null) {
                        binding.coinDetailImage.isInvisible = true
                        binding.coinDetailMain.isInvisible = true
                        binding.coinDetailRow9.isInvisible = true
                        binding.coinDetailRow8.isInvisible = true
                        binding.coinDetailRow7.isInvisible = true
                        binding.coinDetailRow6.isInvisible = true
                        binding.coinDetailRow5.isInvisible = true
                        binding.coinDetailRow4.isInvisible = true
                        binding.coinDetailRow3.isInvisible = true
                        binding.coinDetailRow2.isInvisible = true
                        binding.coinDetailRow1.isInvisible = true

                        binding.coinDetailProgressBar.isVisible = true

                        delay(1000)

                    } else if (coinDetail.name != "errorCoin") {
                        withContext(Dispatchers.Main) {
                            Picasso.get().load(coinDetail.image).into(binding.coinDetailImage)
                            binding.coinDetailMain.setText(coinDetail.name)
                            binding.coinDetailRow9.setText("Current Price: ${coinDetail.currentPrice}")
                            binding.coinDetailRow8.setText("Market Cap: ${coinDetail.marketCap}")
                            binding.coinDetailRow7.setText("Market Cap Rank: ${coinDetail.marketCapRank}")
                            binding.coinDetailRow6.setText("total volume: ${coinDetail.totalVolume}")
                            binding.coinDetailRow5.setText("total supply: ${coinDetail.totalSupply}")
                            binding.coinDetailRow4.setText("Circulating Supply: ${coinDetail.circulatingSupply}")
                            binding.coinDetailRow3.setText("Max Supply: ${coinDetail.maxSupply}")
                            binding.coinDetailRow2.setText("ATH: ${coinDetail.ath}")
                            binding.coinDetailRow1.setText("ATH Change Percentage : ${coinDetail.athChangePercentage}")
                        }

                        binding.coinDetailProgressBar.isInvisible = true
                        binding.coinDetailImage.isVisible = true
                        binding.coinDetailMain.isVisible = true
                        binding.coinDetailRow9.isVisible = true
                        binding.coinDetailRow8.isVisible = true
                        binding.coinDetailRow7.isVisible = true
                        binding.coinDetailRow6.isVisible = true
                        binding.coinDetailRow5.isVisible = true
                        binding.coinDetailRow4.isVisible = true
                        binding.coinDetailRow3.isVisible = true
                        binding.coinDetailRow2.isVisible = true
                        binding.coinDetailRow1.isVisible = true

                        loading = false

                    } else {
                        withContext(Dispatchers.Main) {
                            binding.coinDetailMain.setText("Error")
                        }

                        binding.coinDetailImage.isInvisible = true
                        binding.coinDetailMain.isVisible = true
                        binding.coinDetailRow9.isInvisible = true
                        binding.coinDetailRow8.isInvisible = true
                        binding.coinDetailRow7.isInvisible = true
                        binding.coinDetailRow6.isInvisible = true
                        binding.coinDetailRow5.isInvisible = true
                        binding.coinDetailRow4.isInvisible = true
                        binding.coinDetailRow3.isInvisible = true
                        binding.coinDetailRow2.isInvisible = true
                        binding.coinDetailRow1.isInvisible = true

                        loading = false

                    }
                }
            }
        }
    }



