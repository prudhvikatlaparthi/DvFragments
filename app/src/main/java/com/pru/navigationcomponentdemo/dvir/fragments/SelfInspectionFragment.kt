package com.pru.navigationcomponentdemo.dvir.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pru.navigationcomponentdemo.R
import com.pru.navigationcomponentdemo.databinding.FragmentSelfInspectionBinding
import com.pru.navigationcomponentdemo.models.DVirConfigMaster

class SelfInspectionFragment : Fragment(R.layout.fragment_self_inspection) {
    private lateinit var binding: FragmentSelfInspectionBinding
    private var driverInspectionList = mutableListOf<DVirConfigMaster>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSelfInspectionBinding.bind(view)

    }

    fun updateView(driverInspectionList: MutableList<DVirConfigMaster>) {
        this.driverInspectionList = driverInspectionList
    }

    fun validateView(moveListener: () -> Unit) {
        /*val checkListSize = driverInspectionList.filter { it.isChecked }.size
        if (checkListSize != driverInspectionList.size) {
            // TODO: AlertDialog Okay click
            moveListener.invoke()
        }*/
        moveListener.invoke()
    }
}