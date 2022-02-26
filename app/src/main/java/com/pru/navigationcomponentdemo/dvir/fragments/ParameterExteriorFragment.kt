package com.pru.navigationcomponentdemo.dvir.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.pru.navigationcomponentdemo.R
import com.pru.navigationcomponentdemo.databinding.FragmentParameterExteriorBinding

class ParameterExteriorFragment : Fragment(R.layout.fragment_parameter_exterior) {
    private lateinit var binding: FragmentParameterExteriorBinding
    private val tabList = mutableListOf<TabLayout.Tab>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentParameterExteriorBinding.bind(view)
        binding.tabView.removeAllTabs()
        binding.tabView.addTab(binding.tabView.newTab().apply {
            text = "Front"
            select()
        })
        binding.tabView.addTab(binding.tabView.newTab().apply {
            text = "Side1"
        })
        binding.tabView.addTab(binding.tabView.newTab().apply {
            text = "Side2"
        })
        binding.tabView.addTab(binding.tabView.newTab().apply {
            text = "Rear"
        })
        binding.tabView.addTab(binding.tabView.newTab().apply {
            text = "Tyre"
        })
        binding.tabView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    binding.selectedTab.text = it.text
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }
}

