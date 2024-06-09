package com.creative.spotifykt.ui.setting.mobiledata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creative.spotifykt.data.model.local.MobileDataUI
import com.creative.spotifykt.usecase.setting.GetSettingMobileDataUseCase
import com.creative.spotifykt.usecase.setting.UpdateMobileDataLimitUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MobileDataViewModel @Inject constructor(
    private val getSettingMobileDataUseCase: GetSettingMobileDataUseCase,
    private val updateMobileDataLimitUseCase: UpdateMobileDataLimitUseCase
) : ViewModel() {
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
        viewModelScope.launch(Dispatchers.Main) {
            updateMobileDataLimitUseCase.execute(
                UpdateMobileDataLimitUseCase.Params(position)
            ).collect {
                settingMobileDataLiveData.value = it
            }
        }
    }
}

sealed class SettingMobileDataState {
    object Empty : SettingMobileDataState()
    data class Success(val data: MobileDataUI) : SettingMobileDataState()
    object Error : SettingMobileDataState()
}