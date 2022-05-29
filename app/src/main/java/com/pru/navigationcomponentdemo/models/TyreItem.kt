package com.pru.navigationcomponentdemo.models

import com.pru.navigationcomponentdemo.utils.Constants

data class TyreItem(
    var tyrePosition : String,
    var isThreadDepthPass: Constants.ItemCheckStatus = Constants.ItemCheckStatus.NONE,
    var idealDepth: Int,
    var currentDepth: Int,
    var isTireRetread: Boolean = false,
    var isAirPressurePass: Constants.ItemCheckStatus = Constants.ItemCheckStatus.NONE,
    var idealPressure: Int,
    var currentPressure: Int,
    var pressureComments: String
)
