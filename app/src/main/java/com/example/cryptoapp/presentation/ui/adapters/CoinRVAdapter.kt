package com.example.cryptoapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.databinding.CoinsRvItemBinding
import com.example.cryptoapp.domain.model.CoinItem
import com.squareup.picasso.Picasso

class CoinRVAdapter(private var coinItemsList: List<CoinItem> = emptyList<CoinItem>(), private var coinItemClickListener: OnCoinItemClickListener ): RecyclerView.Adapter<CoinRVAdapter.CoinRVViewHolder>() {


    inner class CoinRVViewHolder(val itemBinding: CoinsRvItemBinding): RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }


        fun bind(item: CoinItem, action:OnCoinItemClickListener){
            Picasso.get().load(item.image).into(itemBinding.coinImage)
            itemBinding.coinSymbol.text = item.symbol
            itemBinding.coinPrice.text = item.currentPrice.toString()

            itemView.setOnClickListener {
                action.onItemClick(item,bindingAdapterPosition)
            }
        }

        override fun onClick(p0: View?) {
           val position: Int = bindingAdapterPosition

            if (position != RecyclerView.NO_POSITION) {
                coinItemClickListener.onItemClick(position = position, item = coinItemsList[position])
            }
        }
    }


    inner class CoinDiffUtil(private val newCoinItems: List<CoinItem>): DiffUtil.Callback(){
        override fun getOldListSize(): Int = coinItemsList.size

        override fun getNewListSize(): Int = newCoinItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return coinItemsList[oldItemPosition].symbol == newCoinItems[newItemPosition].symbol
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return when{
                coinItemsList[oldItemPosition].symbol != newCoinItems[newItemPosition].symbol -> {
                    false
                }

                coinItemsList[oldItemPosition].currentPrice != newCoinItems[newItemPosition].currentPrice -> {
                    false
                }

                coinItemsList[oldItemPosition].image != newCoinItems[newItemPosition].image -> {
                    false

                } else -> true
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinRVViewHolder {
        return CoinRVViewHolder(CoinsRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: CoinRVViewHolder, position: Int) {
        val coinItem: CoinItem = coinItemsList[position]

        holder.bind(coinItem,coinItemClickListener)

    }


    override fun getItemCount(): Int = coinItemsList.size

    fun setData(newCoinsItemList: List<CoinItem>){
        val diffUtil = CoinDiffUtil(newCoinsItemList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        coinItemsList = newCoinsItemList
        diffResult.dispatchUpdatesTo(this)

    }

}

interface OnCoinItemClickListener{
    fun onItemClick(item: CoinItem, position: Int)
}