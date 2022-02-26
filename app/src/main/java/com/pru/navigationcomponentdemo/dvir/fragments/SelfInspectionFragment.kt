package com.pru.navigationcomponentdemo.dvir.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pru.navigationcomponentdemo.R
import com.pru.navigationcomponentdemo.databinding.FragmentSelfInspectionBinding

class SelfInspectionFragment : Fragment(R.layout.fragment_self_inspection) {
    private lateinit var binding: FragmentSelfInspectionBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSelfInspectionBinding.bind(view)

    }
}