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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.FragmentCoinsRvBinding
import com.example.cryptoapp.domain.model.CoinItem
import com.example.cryptoapp.presentation.ui.adapters.CoinRVAdapter
import com.example.cryptoapp.presentation.ui.adapters.OnCoinItemClickListener
import com.example.cryptoapp.presentation.viewModel.fragments.CoinsRVViewModel
import com.example.cryptoapp.presentation.viewModel.shared_viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.math.log

@AndroidEntryPoint
class CoinsRVFragment : Fragment(), OnCoinItemClickListener {

    private var _binding: FragmentCoinsRvBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CoinsRVViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val coinRecyclerView by lazy { CoinRVAdapter(coinItemClickListener = this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       _binding = FragmentCoinsRvBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        updateRecyclerView()

        Log.d(TAG, "LOG: We have reached onViewCreated on CoinsRVFragment ")
    }


    private fun initRecyclerView(){
        binding.coinRV.layoutManager = LinearLayoutManager(context)
        binding.coinRV.adapter = coinRecyclerView
        coinRecyclerView.setData(viewModel.coinList)

        Log.d(TAG, "LOG: The recyclerview has been initialized")
    }


    private fun updateRecyclerView(){
        lifecycleScope.launch {
            var loading = true
            while (loading){
                val emptyCoinList: List<CoinItem> = emptyList()
                if (viewModel.coinList != emptyCoinList ) {
                    withContext(Dispatchers.Main) {
                        binding.RVProgressBar.isInvisible = true
                        coinRecyclerView.setData(viewModel.coinList)
                    }
                    loading = false
                    Log.d(TAG, "LOG: updateRecyclerView still running")
                }else{
                    withContext(Dispatchers.Main) {
                        binding.RVProgressBar.isVisible = true
                    }
                    Log.d(TAG, "LOG: updateRecyclerView still running")
                    delay(1000)
                }
            }
        }
    }


    override fun onItemClick(item: CoinItem, position: Int) {

        sharedViewModel.coinId = item.id
        Log.d(TAG, "LOG: ${item.name} works ")
        findNavController().navigate(R.id.action_coinsRVFragment_to_coinDetailsFragment)


    }


}