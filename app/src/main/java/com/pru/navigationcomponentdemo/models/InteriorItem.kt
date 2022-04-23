package com.pru.navigationcomponentdemo.models

import androidx.annotation.DrawableRes

data class InteriorItem(
    val itemName: String,
    @DrawableRes val itemIcon: Int,
    var itemCheckValidation: ItemCheckValidation = ItemCheckValidation()
)
