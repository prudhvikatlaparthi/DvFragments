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

class DVIRActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDviractivityBinding
    private val dataList = mutableListOf(
        DVIRItem(fragment = SelfInspectionFragment()),
        DVIRItem(fragment = CapturedImageFragment()),
        DVIRItem(fragment = ParameterExteriorFragment()),
    )
    private val pagerAdapter by lazy {
        ViewPagerAdapter(
            fragmentActivity = this, dataList = dataList
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDviractivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vmPager2.adapter = pagerAdapter

        binding.dotsIndicator.setViewPager2(binding.vmPager2)

        binding.leftBtn.setOnClickListener {
            binding.vmPager2.setCurrentItem(binding.vmPager2.currentItem - 1, true)
        }

        binding.rightBtn.setOnClickListener {
            Log.i("Prudhvi Log", "onCreate: ${binding.vmPager2.currentItem}")
            binding.vmPager2.setCurrentItem(binding.vmPager2.currentItem + 1, true)
        }

    }
}