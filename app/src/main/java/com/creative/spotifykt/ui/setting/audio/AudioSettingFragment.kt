package com.creative.spotifykt.ui.setting.audio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.creative.spotifykt.R
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.data.model.local.AppBarUI
import com.creative.spotifykt.data.model.local.ColorStyle
import com.creative.spotifykt.data.model.local.SettingRowUI
import com.creative.spotifykt.data.model.local.TextLabel
import com.creative.spotifykt.databinding.AudioSettingFragmentBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.ui.IAppBarHandler
import com.creative.spotifykt.ui.ISettingRowHandler
import com.creative.spotifykt.ui.setting.SettingListAdapter

class AudioSettingFragment : BaseFragment<AudioSettingFragmentBinding, AudioSettingViewModel>() {

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

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): AudioSettingFragmentBinding =
        AudioSettingFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            settingToolbar.data = AppBarUI(title = TextLabel(getString(R.string.audio_settings), ColorStyle.PRIMARY.value))
            settingToolbar.appBarHandler = object : IAppBarHandler {
                override fun handleBack() {
                    findNavController().navigateUp()
                }
            }
            listAudioSetting.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            listAudioSetting.adapter = settingListAdapter
        }
    }
}