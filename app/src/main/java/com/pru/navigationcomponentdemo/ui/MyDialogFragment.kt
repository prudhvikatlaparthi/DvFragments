package com.pru.navigationcomponentdemo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.pru.navigationcomponentdemo.R
import com.pru.navigationcomponentdemo.databinding.FragmentDialogBinding

class MyDialogFragment : DialogFragment(R.layout.fragment_dialog) {
    private lateinit var binding: FragmentDialogBinding

    override fun onStart() {
        super.onStart()
        requireDialog().window?.setLayout(500, 500)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDialogBinding.bind(view)
    }
}