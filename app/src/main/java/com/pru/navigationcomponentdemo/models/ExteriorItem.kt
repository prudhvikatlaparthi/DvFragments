package com.pru.navigationcomponentdemo.models

data class ExteriorItem(
    val itemName: String,
    var itemCheckValidation: ItemCheckValidation = ItemCheckValidation()
)
