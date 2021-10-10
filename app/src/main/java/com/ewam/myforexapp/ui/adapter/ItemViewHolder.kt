package com.ewam.myforexapp.ui.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.ewam.myforexapp.databinding.ItemRowLayoutBinding
import com.ewam.myforexapp.models.DataModel

class ItemViewHolder(private val binding: ItemRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DataModel) {
        Log.i("Mytag", " ItemViewHolder")
        binding.apply {
            itemDate.text = item.date
            currencyName.text = "USD"
            currencyValue.text = item.rates.get("USD").toString()
            currencyName2.text = "AUD"
            currencyValue2.text = item.rates.get("AUD").toString()
            currencyName3.text = "PLN"
            currencyValue3.text = item.rates.get("PLN").toString()
        }
    }
}