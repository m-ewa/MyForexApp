package com.ewam.myforexapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ewam.myforexapp.databinding.MainFragmentBinding
import com.ewam.myforexapp.ui.adapter.ForexAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()
    private var binding: MainFragmentBinding? = null
    private var adapter: ForexAdapter? = null
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        if (viewModel.forexDataList.value.isNullOrEmpty()) viewModel.getAllForexData()
        adapter = ForexAdapter(this, viewModel) { position ->
            viewModel.position = position
            val action = MainFragmentDirections.actionOpenDetail(position)
            findNavController().navigate(action)
        }
        binding?.forexRecyclerView?.adapter = adapter
        linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewScrollListener()
    }

    private fun setRecyclerViewScrollListener() {
        binding?.forexRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                val lastVisibleItemPosition =
                    (binding?.forexRecyclerView?.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    viewModel.getNextData()
                }
            }
        })
    }
}
