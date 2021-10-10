package com.ewam.myforexapp.ui.adapter

import android.util.Log
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

//    var onItemClick: (Int) -> Unit = {}

    //    var items: ArrayList<Item> = arrayListOf()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
    var forexList: List<DataModel> = listOf()

    init {
        Log.i("Mytag", " Adapter init")
//        viewModel.forexDataList.observe(fragment.viewLifecycleOwner) { viewModelForexList ->
//            viewModelForexList?.let {
//                forexList = viewModelForexList
//                notifyDataSetChanged()
//            }
//        }
        viewModel.forexDataList.observe(fragment.viewLifecycleOwner, { viewModelForexList ->
            viewModelForexList?.let {
                Log.i("Mytag", " observe")
//                forexList.clear()
                forexList = viewModelForexList
                notifyDataSetChanged()
            } ?: run {
                Log.i("Mytag", " run")
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
        Log.i("Mytag", " onBindViewHolder")
        holder.itemView.setOnClickListener{
            onItemClick(position)
        }
        holder.bind(item)
    }

    override fun getItemCount() = forexList.size
}