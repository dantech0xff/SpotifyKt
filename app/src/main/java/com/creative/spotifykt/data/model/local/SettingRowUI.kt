package com.creative.spotifykt.data.model.local

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MainSettingUI(
    val settingList: List<SettingRowUI?>? = null
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class SettingRowUI(
    val rowType: String? = null,
    val settingId: String? = null,
    val deeplink: String? = null,
    val title: TextLabel? = null,
    val subTitle: TextLabel? = null,
    val iconUrl: String? = null,
    val sliderUI: SliderUI? = null,
    val switchUI: SwitchUI? = null,
    val checkedUI: CheckedUI? = null,
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class CheckedUI(
    val isChecked: Boolean? = null,
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class SwitchUI(
    val isOn: Boolean? = null,
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class SliderUI(
    val minValue: Float? = null,
    val maxValue: Float? = null,
    val currentValue: Float? = null,
    val minText: TextLabel? = null,
    val maxText: TextLabel? = null,
): Parcelable

enum class SettingRowType(val value: String) {
    ROW_SETTING_TEXT("ROW_SETTING_TEXT"),
    ROW_SECTION_SEPARATE("ROW_SECTION_SEPARATE"),
    ROW_SETTING_SWITCH("ROW_SETTING_SWITCH"),
    ROW_SETTING_CHECKBOX("ROW_SETTING_CHECKBOX"),
    ROW_SETTING_SLIDER("ROW_SETTING_SLIDER"),
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