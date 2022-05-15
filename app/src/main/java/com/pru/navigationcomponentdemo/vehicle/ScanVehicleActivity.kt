package com.pru.navigationcomponentdemo.vehicle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import com.pru.navigationcomponentdemo.databinding.ActivityScanVehicleBinding
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScanVehicleActivity : AppCompatActivity(),ZXingScannerView.ResultHandler {
    private lateinit var binding: ActivityScanVehicleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanVehicleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.zScannerView.setResultHandler(this)
    }

    override fun onResume() {
        super.onResume()
        binding.zScannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        binding.zScannerView.stopCamera()
    }

    override fun handleResult(rawResult: Result?) {
        Log.i("Prudhvi Log", "handleResult: $rawResult")
    }
}