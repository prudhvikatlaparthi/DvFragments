package com.pru.navigationcomponentdemo.dvir.fragments

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.pru.navigationcomponentdemo.BuildConfig
import com.pru.navigationcomponentdemo.R
import com.pru.navigationcomponentdemo.databinding.FragmentCameraCaptureBinding
import com.pru.navigationcomponentdemo.utils.CommonUtils.displayToast
import com.pru.navigationcomponentdemo.utils.Constants.kErrorMessage
import com.pru.navigationcomponentdemo.utils.createImageFile
import java.io.File
import java.io.IOException

class CapturedImageFragment : Fragment(R.layout.fragment_camera_capture) {
    private lateinit var binding: FragmentCameraCaptureBinding
    private var mPhotoFile: File? = null
    private var selectedPosition = -1
    private val capturedImages = HashMap<Int, File?>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCameraCaptureBinding.bind(view)
        capturedImages[0] = null
        capturedImages[1] = null
        capturedImages[2] = null
        binding.imgC1.setOnClickListener {
            openCamera(0)
        }
        binding.imgC2.setOnClickListener {
            openCamera(1)
        }
        binding.imgC3.setOnClickListener {
            openCamera(2)
        }
        binding.imgClear1.setOnClickListener {
            capturedImages[0] = null
            binding.imgC1.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_car
                )
            )
            it.isVisible = false
        }
    }

    private val cameraActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            prepareImageUpload()
        }
    }

    private fun prepareImageUpload() {
        mPhotoFile?.let {
            setImage(it, getSelectedImageView())
            showClearImage()
//            val base64Data = getBase64String(decodeFile(it))
        } ?: requireContext().displayToast(kErrorMessage)
    }

    private fun getSelectedImageView(): ImageView {
        return when (selectedPosition) {
            0 -> binding.imgC1
            1 -> binding.imgC2
            2 -> binding.imgC3
            else -> {
                binding.imgC1
            }
        }
    }

    private fun showClearImage() {
        when (selectedPosition) {
            0 -> binding.imgClear1.isVisible = true
            1 -> TODO()
            2 -> TODO()
            else -> {
                TODO()
            }
        }
    }

    private fun setImage(file: File, imageView: ImageView) {
        capturedImages[selectedPosition] = file
        imageView.setImageURI(Uri.fromFile(file))
    }

    private fun openCamera(position: Int) {
        selectedPosition = position
        try {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            mPhotoFile = requireContext().createImageFile()
            val photoUri: Uri? = mPhotoFile?.let {
                FileProvider.getUriForFile(
                    requireContext(),
                    BuildConfig.APPLICATION_ID + ".provider",
                    it
                )
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            cameraActivityResultLauncher.launch(intent)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
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