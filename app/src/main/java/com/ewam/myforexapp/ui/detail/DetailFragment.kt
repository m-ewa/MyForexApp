package com.ewam.myforexapp.ui.detail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ewam.myforexapp.databinding.FragmentDetailBinding
import com.ewam.myforexapp.databinding.ItemCurrencyValueBinding
import com.ewam.myforexapp.ui.main.MainViewModel


class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private val viewModel: MainViewModel by activityViewModels()
    private var position = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        arguments?.getInt("ItemPosition")?.let {
            position = it
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemsAdapter = UsersAdapter(requireContext(), mapToArray() as ArrayList<DayCurrency>)

        binding?.apply {
            currency = viewModel.forexDataList.value?.get(position)
            currencyList.apply {
                adapter = itemsAdapter
            }
        }
    }

    private fun mapToArray(): List<DayCurrency> {
        val myList: ArrayList<DayCurrency> = arrayListOf()
        val map = viewModel.forexDataList.value?.get(position)?.rates
        viewModel.forexDataList.value?.get(position)?.rates?.keys?.forEachIndexed { index, currencyName ->
            val value: Double? = map?.get(currencyName)
            myList.add(DayCurrency(index, currencyName, (value as Double)))
        }

        return myList.toList()
    }

    data class DayCurrency(
        val index: Int,
        val name: String,
        val value: Double
    )

    class UsersAdapter(context: Context, users: ArrayList<DayCurrency>) :
        ArrayAdapter<DayCurrency>(context, 0, users) {

        private var list = ArrayList<DayCurrency>()

        init {
            list.addAll(users)
        }

        override fun getCount(): Int {
            Log.i("Mytag", "super.getCount() ${super.getCount()}")
            return list.size
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            val itemBinding: ItemCurrencyValueBinding =
                convertView?.tag as? ItemCurrencyValueBinding ?: ItemCurrencyValueBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

            itemBinding.name.text = list[position].name
            itemBinding.value.text = list[position].value.toString()

            return itemBinding.root
        }
    }

}
