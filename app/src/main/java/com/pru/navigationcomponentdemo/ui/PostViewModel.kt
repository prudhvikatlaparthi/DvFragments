package com.pru.navigationcomponentdemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pru.navigationcomponentdemo.utils.OneTimeEvent
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    private val _data = MutableLiveData<OneTimeEvent<String>>()
    val data: LiveData<OneTimeEvent<String>> = _data

    fun addData(value: String) {
        viewModelScope.launch {
            _data.value = OneTimeEvent(content = value)
        }
    }
}