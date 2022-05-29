package com.pru.navigationcomponentdemo.dvir

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.pru.navigationcomponentdemo.R
import com.pru.navigationcomponentdemo.databinding.FragmentTyreDialogBinding
import com.pru.navigationcomponentdemo.models.TyreItem
import com.pru.navigationcomponentdemo.utils.CommonUtils.getHeight
import com.pru.navigationcomponentdemo.utils.CommonUtils.getWidth


class TyreDialogFragment(
    private val key: String,
    private val tyreInspectionList: LinkedHashMap<String, TyreItem>
) : DialogFragment(R.layout.fragment_tyre_dialog) {

    private lateinit var binding: FragmentTyreDialogBinding
    private var listener: Listener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as Listener
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStart() {
        super.onStart()
        val width: Int = (requireContext().getWidth() * 0.96).toInt()
        val height: Int = (requireContext().getHeight() * 0.7).toInt()

        requireDialog().window?.setLayout(width, height)
        requireDialog().window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTyreDialogBinding.bind(view)
        setViews()
        setEvents()
    }

    private fun setEvents() {
        binding.llThreadDepth.setOnClickListener {
            binding.tvThreadDepth.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.holo_green_dark
                )
            )
            binding.vTD.isVisible = true

            binding.tvAirPressure.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            binding.vAP.isVisible = false

            binding.tireDepthWrapper.isVisible = true
            binding.tirePressureWrapper.isVisible = false
        }
        binding.llAirPressure.setOnClickListener {
            binding.tvThreadDepth.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.black
                )
            )
            binding.vTD.isVisible = false

            binding.tvAirPressure.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.holo_green_dark
                )
            )
            binding.vAP.isVisible = true

            binding.tireDepthWrapper.isVisible = false
            binding.tirePressureWrapper.isVisible = true
        }

        binding.btnSave.setOnClickListener {
            val tyreItem: TyreItem? = tyreInspectionList[key]
            tyreItem?.currentDepth = binding.depthValue.text.toString().toIntOrNull() ?: 0
            tyreItem?.pressureComments = binding.comments.text.toString()
            tyreInspectionList[key] = tyreItem!!
            listener?.onTyreDialogSave(tyreInspectionList)
            dismiss()
        }
    }

    private fun setViews() {
        val tyreItem = tyreInspectionList[key]
        binding.headerTitle.setText(tyreItem?.tyrePosition)
        binding.depthValue.setText(
            if ((tyreItem?.currentDepth ?: 0) > 0) tyreItem?.currentDepth?.toString() else ""
        )
        binding.comments.setText(tyreItem?.pressureComments)
    }

    interface Listener {
        fun onTyreDialogSave(tyreInspectionList: LinkedHashMap<String, TyreItem>)
    }
}