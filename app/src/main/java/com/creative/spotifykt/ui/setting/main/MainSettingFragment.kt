package com.creative.spotifykt.ui.setting.main

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.creative.spotifykt.R
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.data.model.local.AppBarUI
import com.creative.spotifykt.data.model.local.SettingActionId
import com.creative.spotifykt.data.model.local.SettingRowUI
import com.creative.spotifykt.data.model.local.TextLabel
import com.creative.spotifykt.databinding.MainSettingFragmentBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.ui.IAppBarHandler
import com.creative.spotifykt.ui.setting.SettingListAdapter

class MainSettingFragment : BaseFragment<MainSettingFragmentBinding, MainSettingViewModel>() {

    private val settingListAdapter by lazy {
        SettingListAdapter()
    }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        MainSettingFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    private fun handleSetting(settingItem: SettingRowUI) {
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

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            settingToolbar.appBarHandler = object : IAppBarHandler {
                override fun handleBack() {
                    activity?.onBackPressedDispatcher?.onBackPressed()
                }
            }
            settingToolbar.data = AppBarUI(
                TextLabel(
                    getString(R.string.settings)
                )
            )

            settingList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = settingListAdapter
                addItemDecoration(object : ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        super.getItemOffsets(outRect, view, parent, state)
                    }
                })
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