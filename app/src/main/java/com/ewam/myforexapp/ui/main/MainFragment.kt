package com.ewam.myforexapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ewam.myforexapp.databinding.MainFragmentBinding
import com.ewam.myforexapp.ui.adapter.ForexAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()
    private var binding: MainFragmentBinding? = null
    private var adapter: ForexAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        if(viewModel.forexDataList.value.isNullOrEmpty()) viewModel.getAllForexData()
        adapter = ForexAdapter(this, viewModel){ position ->
            //TODO navigacja do detailu
            viewModel.position = position
            val action = MainFragmentDirections.actionOpenDetail(position)
            findNavController().navigate(action)
        }
        binding?.forexRecyclerView?.adapter = adapter
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}