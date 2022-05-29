package com.pru.navigationcomponentdemo.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast

object CommonUtils {
    fun Context.displayToast(message: String) {
        if (message.isBlank()){
            return
        }
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun Context.getHeight(): Int = resources.displayMetrics.heightPixels

    fun Context.getWidth(): Int = resources.displayMetrics.widthPixels
}