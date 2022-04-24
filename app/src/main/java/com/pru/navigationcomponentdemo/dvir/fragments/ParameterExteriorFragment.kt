package com.pru.navigationcomponentdemo.dvir.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.pru.navigationcomponentdemo.R
import com.pru.navigationcomponentdemo.databinding.FragmentParameterExteriorBinding
import com.pru.navigationcomponentdemo.models.ExteriorItem
import com.pru.navigationcomponentdemo.models.ItemCheckValidation
import com.pru.navigationcomponentdemo.utils.Constants
import com.pru.navigationcomponentdemo.utils.ObjectHolder

class ParameterExteriorFragment : Fragment(R.layout.fragment_parameter_exterior) {
    private lateinit var binding: FragmentParameterExteriorBinding
    private val exteriorInspectionList = linkedMapOf<String, ArrayList<ExteriorItem>>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentParameterExteriorBinding.bind(view)
        ObjectHolder.getSelectedVehicle()?.let {
            if (it.vehicleType == "lower_veh") {
                binding.twoVehicler.root.isVisible = true
            } else {
                binding.fourVehicler.root.isVisible = true
            }
        } ?: kotlin.run {
            binding.fourVehicler.root.isVisible = true
        }
        initViews()
        onclickListeners()
    }

    private fun initViews() {
        for (item in exteriorInspectionList) {
            binding.tabView.addTab(binding.tabView.newTab().apply {
                text = item.key
            })
        }
        onTabChange(binding.tabView.getTabAt(0))
    }

    private fun onclickListeners() {
        binding.tabView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                onTabChange(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.fourVehicler.frontLayout.frontMirror.setOnClickListener {
            toggleStatus(it, "FRONT MIRROR",Constants.DVREnum.ParameterExteriorInspection.FrontView.title)
        }

        binding.fourVehicler.frontLayout.wShieldWiper.setOnClickListener {
            toggleStatus(it, "WINDSHIELD WIPER",Constants.DVREnum.ParameterExteriorInspection.FrontView.title)
        }

        binding.fourVehicler.side1Layout.sideMirror.setOnClickListener {
            toggleStatus(it, "SIDE MIRROR",Constants.DVREnum.ParameterExteriorInspection.LeftSideView.title)
        }

        binding.fourVehicler.side1Layout.windows.setOnClickListener {
            toggleStatus(it, "WINDOWS",Constants.DVREnum.ParameterExteriorInspection.LeftSideView.title)
        }
    }

    private fun toggleStatus(it: View, itemName: String, key: String) {
        if (it.tag == null) {
            val selectedItem =
                exteriorInspectionList[key]
            selectedItem?.forEach { exItem ->
                if (exItem.itemName == itemName) {
                    it.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            android.R.color.holo_green_light
                        )
                    )
                    exItem.itemCheckValidation =
                        ItemCheckValidation(itemCheckStatus = Constants.ItemCheckStatus.PASS)
                    it.tag = exItem
                }
            }
        } else {
            val exItem = it.tag as ExteriorItem
            when (exItem.itemCheckValidation.itemCheckStatus) {
                Constants.ItemCheckStatus.NONE -> {
                    exItem.itemCheckValidation =
                        ItemCheckValidation(Constants.ItemCheckStatus.PASS)
                    it.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            android.R.color.holo_green_light
                        )
                    )
                }
                Constants.ItemCheckStatus.PASS -> {
                    exItem.itemCheckValidation =
                        ItemCheckValidation(Constants.ItemCheckStatus.FAIL)
                    it.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            android.R.color.holo_red_light
                        )
                    )
                }
                else -> {
                    exItem.itemCheckValidation =
                        ItemCheckValidation(Constants.ItemCheckStatus.NONE)
                    it.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            android.R.color.white
                        )
                    )
                }
            }
            it.tag = exItem
        }
    }

    private fun onTabChange(tab: TabLayout.Tab?) {
        if (binding.twoVehicler.root.isVisible) {
            // two wheeler
            tab?.let {
                when (it.text) {
                    "Front" -> {
                        binding.twoVehicler.frontLayout.root.isVisible = true
                        binding.twoVehicler.side1Layout.root.isVisible = false
                    }
                    "Side1" -> {
                        binding.twoVehicler.frontLayout.root.isVisible = false
                        binding.twoVehicler.side1Layout.root.isVisible = true
                    }
                    else -> {}
                }
            }
        } else {
            // four wheeler
            tab?.let {
                when (it.text) {
                    "Front" -> {
                        binding.fourVehicler.frontLayout.root.isVisible = true
                        binding.fourVehicler.side1Layout.root.isVisible = false
                    }
                    "Side1" -> {
                        binding.fourVehicler.frontLayout.root.isVisible = false
                        binding.fourVehicler.side1Layout.root.isVisible = true
                    }
                    else -> {}
                }
            }
        }
    }

    fun updateView(data: LinkedHashMap<String, ArrayList<ExteriorItem>>) {
        exteriorInspectionList.clear()
        exteriorInspectionList.putAll(data)
    }
}

