package com.creative.spotifykt.ui.setting.main

import androidx.lifecycle.MutableLiveData
import com.creative.spotifykt.R
import com.creative.spotifykt.data.model.local.SettingActionId
import com.creative.spotifykt.data.model.local.SettingRowItem
import com.creative.spotifykt.data.model.local.SettingRowType
import com.creative.spotifykt.core.viewmodel.BaseViewModel

class MainSettingViewModel : BaseViewModel() {

    val listSettingLiveData: MutableLiveData<List<SettingRowItem>> = MutableLiveData()

    fun getListSetting() {
        listSettingLiveData.value = mutableListOf(
            SettingRowItem(
                SettingRowType.ROW_SETTING, SettingActionId.SETTING_MOBILE_DATA,
                "Dữ liệu di động", "Spotify Lite đã dùng 69 kb trong tháng này"
            ),
            SettingRowItem(
                SettingRowType.ROW_SETTING, SettingActionId.SETTING_STORAGE,
                "Bộ nhớ", "Spotify Lite đã dùng 99 MB"
            ),
            SettingRowItem(
                SettingRowType.ROW_SETTING, SettingActionId.SETTING_AUDIO_VOLUME,
                "Cài đặt âm thanh", "Chất lượng âm thanh: Cơ bản"
            ),
            SettingRowItem(
                SettingRowType.ROW_SETTING, SettingActionId.SETTING_DOWNLOAD,
                "Cài đặt tải xuống", "Chỉ tải xuống qua Wi-Fi"
            ),
            SettingRowItem(
                SettingRowType.ROW_SETTING, SettingActionId.SETTING_ADULT_CONTENT,
                "Nội dung phản cảm", "Cho phép nội dung phản cảm"
            ),
            SettingRowItem(
                SettingRowType.ROW_SETTING, SettingActionId.SETTING_ACCOUNT,
                "Tài khoản", "Tài khoản Free"
            ),
            SettingRowItem(
                SettingRowType.ROW_SETTING, SettingActionId.SETTING_ABOUT,
                "Giới thiệu", "Phiên bản Spotify Lite 1.8.9.52-rc"
            ),
            SettingRowItem(SettingRowType.ROW_SECTION_SEPARATE, SettingActionId.SETTING_EMPTY),
            SettingRowItem(
                SettingRowType.ROW_SETTING, SettingActionId.SETTING_TRY_SPOTIFY,
                "Thử Spotify Music", "", R.mipmap.ic_launcher
            ),
            SettingRowItem(
                SettingRowType.ROW_SETTING, SettingActionId.SETTING_LOGOUT,
                "Đăng xuất", "", R.mipmap.ic_launcher_round
            )
        )
    }

}