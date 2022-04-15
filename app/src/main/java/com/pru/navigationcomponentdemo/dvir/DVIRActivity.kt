package com.pru.navigationcomponentdemo.dvir

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.pru.navigationcomponentdemo.databinding.ActivityDviractivityBinding
import com.pru.navigationcomponentdemo.dvir.adapters.ViewPagerAdapter
import com.pru.navigationcomponentdemo.dvir.fragments.CapturedImageFragment
import com.pru.navigationcomponentdemo.dvir.fragments.ParameterExteriorFragment
import com.pru.navigationcomponentdemo.dvir.fragments.SelfInspectionFragment
import com.pru.navigationcomponentdemo.models.DVIRItem
import com.pru.navigationcomponentdemo.models.DVInspectionResponse
import com.pru.navigationcomponentdemo.models.DVirConfigMaster
import com.pru.navigationcomponentdemo.utils.Constants

class DVIRActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDviractivityBinding
    private val dataList = mutableListOf<DVIRItem>()
    private val selfInspectionFragment: SelfInspectionFragment by lazy { SelfInspectionFragment() }
    private val capturedImageFragment: CapturedImageFragment by lazy { CapturedImageFragment() }
    private val parameterExteriorFragment: ParameterExteriorFragment by lazy { ParameterExteriorFragment() }
    private val pagerAdapter by lazy {
        ViewPagerAdapter(
            fragmentActivity = this, dataList = dataList
        )
    }
    private val driverInspectionList = mutableListOf<DVirConfigMaster>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDviractivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataList.add(DVIRItem(selfInspectionFragment))
        dataList.add(DVIRItem(capturedImageFragment))
        dataList.add(DVIRItem(parameterExteriorFragment))

        binding.vmPager2.adapter = pagerAdapter

        binding.dotsIndicator.setViewPager2(binding.vmPager2)

        binding.leftBtn.setOnClickListener {
            binding.vmPager2.setCurrentItem(binding.vmPager2.currentItem - 1, true)
        }

        binding.rightBtn.setOnClickListener {
            Log.i("Prudhvi Log", "onCreate: ${binding.vmPager2.currentItem}")
            when (binding.vmPager2.currentItem) {
                0 -> {
                    selfInspectionFragment.validateView {
                        binding.vmPager2.setCurrentItem(binding.vmPager2.currentItem + 1, true)
                    }
                }
            }
        }
        // API call
//        onresponse
        val response = DVInspectionResponse(dvirConfigMasterList = listOf())
        if (response.dvirConfigMasterList?.isNotEmpty() == true) {
            response.dvirConfigMasterList.forEach {
                if (it.dvrView == Constants.DVREnum.DriverInspection.value) {
                    driverInspectionList.add(it)
                }
            }
            selfInspectionFragment.updateView(driverInspectionList)
        }
    }
}