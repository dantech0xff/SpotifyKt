package com.creative.spotifykt.ui

import com.creative.spotifykt.data.model.local.SettingRowUI

/**
 * Created by dan on 03/09/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

interface ISettingRowHandler {
    fun onClick(settingRowUI: SettingRowUI)
    fun onSwitch(settingRowUI: SettingRowUI, isChecked: Boolean)
    fun onChecked(settingRowUI: SettingRowUI, isChecked: Boolean)
    fun onSlider(settingRowUI: SettingRowUI, value: Float)
}