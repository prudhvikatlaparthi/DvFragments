package com.pru.navigationcomponentdemo.utils

object Constants {
    enum class DVREnum(val value: String) {
        DriverInspection("driver_inspection"),
        ParameterInteriorInspection("internal"),
    }

    enum class ItemCheckStatus {
        NONE,
        PASS,
        FAIL
    }
}