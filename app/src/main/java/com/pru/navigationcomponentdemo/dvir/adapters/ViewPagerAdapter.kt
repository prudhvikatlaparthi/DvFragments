package com.pru.navigationcomponentdemo.dvir.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pru.navigationcomponentdemo.models.DVIRItem


class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val dataList: List<DVIRItem>) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return dataList[position].fragment
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}