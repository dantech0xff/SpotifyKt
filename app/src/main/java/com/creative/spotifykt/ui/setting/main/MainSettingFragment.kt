package com.creative.spotifykt.ui.setting.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.creative.spotifykt.R
import com.creative.spotifykt.databinding.ItemSettingRowBinding
import com.creative.spotifykt.databinding.LayoutToolbarBinding
import com.creative.spotifykt.databinding.MainSettingFragmentBinding
import com.creative.spotifykt.data.model.local.SettingActionId
import com.creative.spotifykt.data.model.local.SettingRowItem
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.ui.setting.SettingRowViewWrapper
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.core.dpToPixelsInt

class MainSettingFragment : BaseFragment<MainSettingFragmentBinding, MainSettingViewModel>(), View.OnClickListener {

    private lateinit var settingToolbar: LayoutToolbarBinding

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        MainSettingFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.listSettingLiveData.observe(this) { listSettingRows ->
            requireViewBinding().settingListItem.removeAllViews()
            listSettingRows.forEach { rowItem ->
                requireViewBinding().settingListItem.addView(
                    SettingRowViewWrapper(ItemSettingRowBinding.inflate(LayoutInflater.from(requireContext()))).apply {
                        bindData(rowItem) { row ->
                            handleSetting(row)
                        }
                    }.viewBinding.root,
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        bottomMargin = requireContext().dpToPixelsInt(2)
                    }
                )
            }
        }
    }

    private fun handleSetting(settingItem: SettingRowItem) {
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

    override fun onClick(v: View) {
        when (v) {
            settingToolbar.settingBackNav -> {
                requireActivity().onBackPressed()
            }
        }
    }

    override fun setupView(savedInstanceState: Bundle?) {
        settingToolbar = requireViewBinding().settingToolbar
        settingToolbar.settingBackNav.setOnClickListener(this)
        viewModel.getListSetting()
    }
}