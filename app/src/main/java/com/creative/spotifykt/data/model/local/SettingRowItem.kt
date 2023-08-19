package com.creative.spotifykt.data.model.local

import com.creative.spotifykt.core.BaseModelData

data class SettingRowItem(val rowType: SettingRowType,
                          val settingId: String = "",
                          val title: String = "",
                          val subTitle: String = "",
                          val iconRes: Int = 0) : BaseModelData

enum class SettingRowType{
    ROW_SETTING, ROW_SECTION_SEPARATE, ROW_SETTING_SWITCH, ROW_SETTING_CHECKBOX, ROW_SETTING_SLIDER
}

object SettingActionId {
    const val OPEN_SETTING = "com.debugger.spotifylite.OPEN_SETTING"
    const val SETTING_EMPTY = "com.debugger.spotifylite.SETTING_EMPTY"
    const val SETTING_MOBILE_DATA = "com.debugger.spotifylite.SETTING_MOBILE_DATA"
    const val SETTING_STORAGE = "com.debugger.spotifylite.SETTING_STORAGE"
    const val SETTING_AUDIO_VOLUME = "com.debugger.spotifylite.SETTING_VOLUME"
    const val SETTING_DOWNLOAD = "com.debugger.spotifylite.SETTING_DOWNLOAD"
    const val SETTING_ADULT_CONTENT = "com.debugger.spotifylite.SETTING_ADULT_CONTENT"
    const val SETTING_ACCOUNT = "com.debugger.spotifylite.SETTING_ACCOUNT"
    const val SETTING_ABOUT = "com.debugger.spotifylite.SETTING_ABOUT"
    const val SETTING_TRY_SPOTIFY = "com.debugger.spotifylite.SETTING_TRY_SPOTIFY"
    const val SETTING_LOGOUT = "com.debugger.spotifylite.SETTING_LOGOUT"

    const val SETTING_AUDIO_QUALITY_BASIC = "com.debugger.spotifylite.SETTING_AUDIO_QUALITY_BASIC"
    const val SETTING_AUDIO_QUALITY_MEDIUM = "com.debugger.spotifylite.SETTING_AUDIO_QUALITY_BASIC"
    const val SETTING_AUDIO_QUALITY_HIGH = "com.debugger.spotifylite.SETTING_AUDIO_QUALITY_BASIC"

    const val SETTING_DATA_SAVER = "com.debugger.spotifylite.SETTING_DATA_SAVER"
    const val SETTING_EQUALIZER = "com.debugger.spotifylite.SETTING_EQUALIZER"
    const val SETTING_CROSS_FADE_SONG = "com.debugger.spotifylite.SETTING_CROSS_FADE_SONG"
    const val SETTING_GAP_LESS_SONG = "com.debugger.spotifylite.SETTING_GAP_LESS_SONG"
    const val SETTING_AUTO_MIX = "com.debugger.spotifylite.SETTING_AUTO_MIX"
    const val SETTING_NORMALIZE_VOLUME = "com.debugger.spotifylite.SETTING_NORMALIZE_VOLUME"

    const val SETTING_DOWNLOAD_CELLULAR = "com.debugger.spotifylite.SETTING_DOWNLOAD_CELLULAR"
    const val SETTING_EXPLICIT_CONTENT = "com.debugger.spotifylite.SETTING_EXPLICIT_CONTENT"
}