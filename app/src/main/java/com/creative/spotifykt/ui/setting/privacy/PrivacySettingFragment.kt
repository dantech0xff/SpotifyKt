package com.creative.spotifykt.ui.setting.privacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.creative.spotifykt.R
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.data.model.local.AppBarUI
import com.creative.spotifykt.data.model.local.ColorStyle
import com.creative.spotifykt.data.model.local.SettingRowUI
import com.creative.spotifykt.data.model.local.TextLabel
import com.creative.spotifykt.databinding.PrivacySettingFragmentBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.ui.IAppBarHandler
import com.creative.spotifykt.ui.ISettingRowHandler
import com.creative.spotifykt.ui.setting.SettingListAdapter

/**
 * Created by dan on 05/09/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

class PrivacySettingFragment : BaseFragment<PrivacySettingFragmentBinding, PrivacySettingViewModel>() {

    private val settingListAdapter: SettingListAdapter by lazy {
        SettingListAdapter(
            settingRowHandler = object : ISettingRowHandler {
                override fun onClick(settingRowUI: SettingRowUI) {}

                override fun onSwitch(settingRowUI: SettingRowUI, isChecked: Boolean) {}

                override fun onChecked(settingRowUI: SettingRowUI, isChecked: Boolean) {}

                override fun onSlider(settingRowUI: SettingRowUI, value: Float) {}
            }
        )
    }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): PrivacySettingFragmentBinding {
        return PrivacySettingFragmentBinding.inflate(
            inflater, container, false
        )
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            settingToolbar.data = AppBarUI(
                title = TextLabel(
                    getString(R.string.privacy_settings),
                    ColorStyle.PRIMARY.value
                )
            )
            settingToolbar.appBarHandler = object : IAppBarHandler {
                override fun handleBack() {
                    findNavController().navigateUp()
                }
            }
            listPrivacySetting.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                context,
                androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false
            )
            listPrivacySetting.adapter = settingListAdapter
        }
    }
}