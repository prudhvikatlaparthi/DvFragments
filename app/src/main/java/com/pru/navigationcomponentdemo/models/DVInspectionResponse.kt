package com.pru.navigationcomponentdemo.models

data class DVInspectionResponse(
    val dvirConfigMasterList: List<DVirConfigMaster>? = null
)

data class DVirConfigMaster(
    var dvrView: String? = null,
    var dvrParamName: String? = null,
    var isChecked: Boolean = false,
)
