package com.pru.navigationcomponentdemo.dvir.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.pru.navigationcomponentdemo.R
import com.pru.navigationcomponentdemo.databinding.FragmentParameterInteriorBinding
import com.pru.navigationcomponentdemo.dvir.adapters.InteriorAdapter
import com.pru.navigationcomponentdemo.models.InteriorItem
import com.pru.navigationcomponentdemo.models.ItemCheckValidation
import com.pru.navigationcomponentdemo.utils.Constants

class ParameterInteriorFragment : Fragment(R.layout.fragment_parameter_interior) {
    private lateinit var binding: FragmentParameterInteriorBinding
    private val interiorItems = arrayListOf<InteriorItem>()
    private val adapter: InteriorAdapter by lazy {
        InteriorAdapter(listener = {
            val selectedItem = interiorItems[it]
            when (selectedItem.itemCheckValidation.itemCheckStatus) {
                Constants.ItemCheckStatus.NONE -> {
                    selectedItem.itemCheckValidation =
                        ItemCheckValidation(Constants.ItemCheckStatus.PASS)
                }
                Constants.ItemCheckStatus.PASS -> {
                    selectedItem.itemCheckValidation =
                        ItemCheckValidation(Constants.ItemCheckStatus.FAIL)
                }
                else -> {
                    selectedItem.itemCheckValidation =
                        ItemCheckValidation(Constants.ItemCheckStatus.NONE)
                }
            }
            interiorItems[it] = selectedItem
            adapter.notifyDataSetChanged()

        }, dataList = interiorItems)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentParameterInteriorBinding.bind(view)
        binding.rcInterior.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcInterior.adapter = adapter
    }

    fun updateView(data: ArrayList<InteriorItem>) {
        interiorItems.clear()
        interiorItems.addAll(data)
    }
}