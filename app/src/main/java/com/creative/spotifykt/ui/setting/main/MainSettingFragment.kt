package com.creative.spotifykt.ui.setting.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.creative.spotifykt.R
import com.creative.spotifykt.core.log
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.data.model.local.AppBarUI
import com.creative.spotifykt.data.model.local.SettingActionId
import com.creative.spotifykt.data.model.local.SettingRowUI
import com.creative.spotifykt.data.model.local.TextLabel
import com.creative.spotifykt.databinding.MainSettingFragmentBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.ui.IAppBarHandler
import com.creative.spotifykt.ui.ISettingRowHandler
import com.creative.spotifykt.ui.setting.SettingListAdapter
import com.creative.spotifykt.utils.handleDeeplinkInternal

class MainSettingFragment : BaseFragment<MainSettingFragmentBinding, MainSettingViewModel>() {

    private val settingListAdapter by lazy {
        SettingListAdapter(
            object : ISettingRowHandler {
                override fun onClick(settingRowUI: SettingRowUI) {
                    handleSettingClick(settingRowUI)
                }

                override fun onSwitch(settingRowUI: SettingRowUI, isChecked: Boolean) {}

                override fun onChecked(settingRowUI: SettingRowUI, isChecked: Boolean) {}

                override fun onSlider(settingRowUI: SettingRowUI, value: Float) {}
            }
        )
    }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        MainSettingFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    private fun handleSettingClick(settingItem: SettingRowUI) {
        if (!findNavController().handleDeeplinkInternal(settingItem.deeplink)) {
            log("SettingFragment", "Deep link not found: ${settingItem.deeplink}")
            when (settingItem.settingId) {
                SettingActionId.SETTING_MOBILE_DATA -> {
                    findNavController().navigate(R.id.action_mainSettingFragment_to_mobileDataFragment)
                }

                SettingActionId.SETTING_STORAGE -> {
                    findNavController().navigate(R.id.action_mainSettingFragment_to_storageFragment)
                }

                SettingActionId.SETTING_AUDIO_VOLUME -> {
                    findNavController().navigate(R.id.action_mainSettingFragment_to_audioSettingFragment)
                }

                SettingActionId.SETTING_DOWNLOAD -> {
                    findNavController().navigate(R.id.action_mainSettingFragment_to_downloadSettingsFragment)
                }

                SettingActionId.SETTING_ADULT_CONTENT -> {
                    findNavController().navigate(R.id.action_mainSettingFragment_to_explicitContentFragment)
                }

                SettingActionId.SETTING_ACCOUNT -> {
                    findNavController().navigate(R.id.action_mainSettingFragment_to_accountSettingFragment)
                }

                SettingActionId.SETTING_ABOUT -> {
                    findNavController().navigate(R.id.action_mainSettingFragment_to_aboutFragment)
                }
            }
        }
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            settingToolbar.appBarHandler = object : IAppBarHandler {
                override fun handleBack() {
                    activity?.finish()
                }
            }
            settingToolbar.data = AppBarUI(TextLabel(getString(R.string.settings)))
            settingList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = settingListAdapter
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.listSetting.observe(viewLifecycleOwner) {
            when (it) {
                is MainSettingListState.Success -> {
                    settingListAdapter.submitList(it.data)
                }
                else -> {}
            }
        }
    }
}