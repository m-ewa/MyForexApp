package com.ewam.myforexapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ewam.myforexapp.databinding.FragmentDetailBinding
import com.ewam.myforexapp.ui.main.MainViewModel

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private val viewmodel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val position = arguments?.getInt("ItemPosition")
        Log.i("Mytag", "Item position = ${position}")
        Log.i("Mytag", "Currency detail = ${position?.let { viewmodel.forexDataList.value?.get(it) }}")


        Log.i("Mytag", "Item position 2= ${viewmodel.position}")
        Log.i("Mytag", "Currency detail2 = ${viewmodel.forexDataList.value?.get(viewmodel.position) }}")


        return binding?.root
    }

}