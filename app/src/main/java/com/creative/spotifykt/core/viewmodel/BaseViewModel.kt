package com.creative.spotifykt.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    private val _msgStringLiveData: MutableLiveData<String> = MutableLiveData()
    val messageString: LiveData<String> = _msgStringLiveData
}