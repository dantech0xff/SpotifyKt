package com.creative.spotifykt.ui.setting.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.creative.spotifykt.core.viewmodel.BaseViewModel
import com.creative.spotifykt.data.model.local.SettingRowUI

class MainSettingViewModel : BaseViewModel() {

    private val listSettingLiveData: MutableLiveData<List<SettingRowUI>> = MutableLiveData()
    val listSetting: LiveData<List<SettingRowUI>> = listSettingLiveData
}