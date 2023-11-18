package com.ashish.cryptoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashish.cryptoapp.R
import com.ashish.cryptoapp.databinding.TopCurrencyLayoutBinding
import com.ashish.cryptoapp.models.CryptoCurrency
import com.bumptech.glide.Glide

class TopMarketAdapter(var context: Context, val list: List<CryptoCurrency>) :
    RecyclerView.Adapter<TopMarketAdapter.TopMarketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMarketViewHolder {
        val binding = TopCurrencyLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return TopMarketViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TopMarketViewHolder, position: Int) {
        val item = list[position]
        holder.binding.topCurrencyNameTextView.text = item.name
        Glide.with(context)
            .load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + item.id + ".png")
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.topCurrencyImageView)
        if (item.quotes!![0].percentChange24h > 0) {
            holder.binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.green))
            holder.binding.topCurrencyChangeTextView.text =
                "+ ${String.format("%.2f", item.quotes[0].percentChange24h)} %"
        } else {
            holder.binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.red))
            holder.binding.topCurrencyChangeTextView.text =
                "${String.format("%.2f", item.quotes[0].percentChange24h)} %"
        }
    }

        override fun getItemCount(): Int {
        return list.size
    }

    inner class TopMarketViewHolder(val binding: TopCurrencyLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}