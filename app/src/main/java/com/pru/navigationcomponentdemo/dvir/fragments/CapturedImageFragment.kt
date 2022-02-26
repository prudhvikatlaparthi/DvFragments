package com.pru.navigationcomponentdemo.dvir.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pru.navigationcomponentdemo.R
import com.pru.navigationcomponentdemo.databinding.FragmentCameraCaptureBinding

class CapturedImageFragment : Fragment(R.layout.fragment_camera_capture) {
    private lateinit var binding: FragmentCameraCaptureBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCameraCaptureBinding.bind(view)

    }
}