package com.ewam.myforexapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ewam.myforexapp.databinding.ItemRowLayoutBinding
import com.ewam.myforexapp.models.DataModel
import com.ewam.myforexapp.ui.main.MainFragment
import com.ewam.myforexapp.ui.main.MainViewModel

class ForexAdapter(
    private val fragment: MainFragment,
    private val viewModel: MainViewModel,
    private val onItemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<ItemViewHolder>() {

    var forexList: List<DataModel> = listOf()

    init {

        viewModel.forexDataList.observe(fragment.viewLifecycleOwner, { viewModelForexList ->
            viewModelForexList?.let {
                forexList = viewModelForexList
                notifyDataSetChanged()
            } ?: run {
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val itemView = binding.root

        val itemHolder = ItemViewHolder(binding)
        itemView.setOnClickListener {
            onItemClick(itemHolder.adapterPosition)
        }

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = forexList[position]
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
        holder.bind(item)
    }

    override fun getItemCount() = forexList.size
}
