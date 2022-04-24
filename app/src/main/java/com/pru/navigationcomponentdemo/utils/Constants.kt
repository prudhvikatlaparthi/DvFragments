package com.pru.navigationcomponentdemo.utils

object Constants {
    enum class DVREnum(val value: String) {
        DriverInspection("driver_inspection"),
        ParameterInteriorInspection("internal");

        enum class ParameterExteriorInspection(val value: String, val title: String) {
            FrontView("front_view", "Front"),
            LeftSideView("left_side_view", "Side1"),
            RightSideView("right_side_view", "Side2"),
            RearView("rear_view", "Rear"),
            TireManagement("tire_management", "Tyre")
        }
    }


    enum class ItemCheckStatus {
        NONE,
        PASS,
        FAIL
    }
}