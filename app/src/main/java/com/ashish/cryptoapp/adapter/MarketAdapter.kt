package com.ashish.cryptoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ashish.cryptoapp.R
import com.ashish.cryptoapp.databinding.CurrencyItemLayoutBinding
import com.ashish.cryptoapp.fragment.HomeFragmentDirections
import com.ashish.cryptoapp.fragment.MarketFragmentDirections
import com.ashish.cryptoapp.models.CryptoCurrency
import com.bumptech.glide.Glide

class MarketAdapter(var context: Context, var list: List<CryptoCurrency>, var type: String) :
    RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarketAdapter.MarketViewHolder {
        val binding = CurrencyItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MarketViewHolder(binding)
    }

    fun upDateData(dataItem: List<CryptoCurrency>) {
        list = dataItem
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MarketAdapter.MarketViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MarketViewHolder(private val binding: CurrencyItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CryptoCurrency) {
            binding.currencyNameTextView.text = item.name
            binding.currencySymbolTextView.text = item.symbol

            Glide.with(context)
                .load("https://s2.coinmarketcap.com/static/img/coins/64x64/" + item.id + ".png")
                .thumbnail(
                    Glide.with(context).load(
                        R.drawable.spinner
                    )
                ).into(binding.currencyImageView)

            Glide.with(context)
                .load("https://s3.coinmarketcap.com/generated/sparklines/web/7d/usd/" + item.id + ".png")
                .thumbnail(
                    Glide.with(context).load(
                        R.drawable.spinner
                    )
                ).into(binding.currencyChartImageView)

            binding.currencyPriceTextView.text =
                "${String.format("$%.02f", item.quotes[0].price)}"

            if (item.quotes != null && item.quotes.isNotEmpty()) {
                if (item.quotes[0].percentChange24h > 0) {
                    binding.currencyChangeTextView.setTextColor(context.resources.getColor(R.color.green))
                    binding.currencyChangeTextView.text =
                        "+ ${String.format("%.2f", item.quotes[0].percentChange24h)} %"
                } else {
                    binding.currencyChangeTextView.setTextColor(context.resources.getColor(R.color.red))
                    binding.currencyChangeTextView.text =
                        "${String.format("%.2f", item.quotes[0].percentChange24h)} %"
                }
            }
            itemView.setOnClickListener {
                if (type == "home") {
                    findNavController(it).navigate(
                        HomeFragmentDirections.actionHomeFragmentToDetailsFragment(item!!)
                    )
                } else if (type == "market") {
                    findNavController(it).navigate(
                        MarketFragmentDirections.actionMarketFragmentToDetailsFragment(item!!)
                    )
                }
            }
        }
    }
}
