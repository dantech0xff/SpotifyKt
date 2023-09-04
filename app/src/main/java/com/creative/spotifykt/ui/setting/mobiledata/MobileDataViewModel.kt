package com.creative.spotifykt.ui.setting.mobiledata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.creative.spotifykt.core.viewmodel.BaseViewModel
import com.creative.spotifykt.data.model.local.MobileDataUI
import com.creative.spotifykt.usecase.setting.GetSettingMobileDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MobileDataViewModel(
    private val getSettingMobileDataUseCase: GetSettingMobileDataUseCase
) : BaseViewModel() {
    private val settingMobileDataLiveData: MutableLiveData<SettingMobileDataState> = MutableLiveData()
    val settingMobileData: LiveData<SettingMobileDataState> = settingMobileDataLiveData

    private var selectedPosition: Int = 0

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getSettingMobileDataUseCase.execute(Unit).collect {
                settingMobileDataLiveData.value = it
            }
        }
    }

    fun getSelectedPosition(): Int {
        return selectedPosition
    }
    fun setSelectedPosition(position: Int) {
        selectedPosition = position
    }
}

sealed class SettingMobileDataState {
    object Empty : SettingMobileDataState()
    data class Success(val data: MobileDataUI) : SettingMobileDataState()
    object Error : SettingMobileDataState()
}