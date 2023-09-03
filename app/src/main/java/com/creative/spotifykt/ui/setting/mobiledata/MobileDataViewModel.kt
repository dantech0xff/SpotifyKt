package com.creative.spotifykt.ui.setting.mobiledata

import com.creative.spotifykt.core.viewmodel.BaseViewModel
import com.creative.spotifykt.usecase.setting.GetSettingMobileDataUseCase

class MobileDataViewModel(
    private val getSettingMobileDataUseCase: GetSettingMobileDataUseCase
) : BaseViewModel() {


}


sealed class SettingMobileDataState {
    object Empty : SettingMobileDataState()
}