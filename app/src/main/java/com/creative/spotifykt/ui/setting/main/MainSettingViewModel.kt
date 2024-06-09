package com.creative.spotifykt.ui.setting.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creative.spotifykt.data.model.local.SettingRowUI
import com.creative.spotifykt.usecase.setting.GetMainSettingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainSettingViewModel @Inject constructor(
    private val getMainSettingUseCase: GetMainSettingUseCase
) : ViewModel() {

    private val listSettingLiveData: MutableLiveData<MainSettingListState> = MutableLiveData()
    val listSetting: LiveData<MainSettingListState> = listSettingLiveData

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getMainSettingUseCase.execute(Unit).collect {
                listSettingLiveData.value = it
            }
        }
    }
}

sealed class MainSettingListState {
    object Loading : MainSettingListState()
    object Error : MainSettingListState()
    object Empty : MainSettingListState()
    data class Success(val data: List<SettingRowUI>) : MainSettingListState()
}