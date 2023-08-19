package com.creative.spotifykt.spotifylite.ui.setting.audio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.creative.spotifykt.R
import com.creative.spotifykt.spotifylite.data.SettingActionId
import com.creative.spotifykt.spotifylite.data.SettingRowItem
import com.creative.spotifykt.spotifylite.data.SettingRowType
import com.creative.spotifykt.databinding.AudioSettingFragmentBinding
import com.creative.spotifykt.databinding.LayoutToolbarBinding
import com.creative.spotifykt.spotifylite.di.component.FragmentComponent
import com.creative.spotifykt.base.ui.BaseFragment
import com.creative.spotifykt.spotifylite.ui.setting.SettingRowViewWrapper

class AudioSettingFragment : BaseFragment<AudioSettingFragmentBinding, AudioSettingViewModel>(), View.OnClickListener {
    private lateinit var settingToolbarBinding: LayoutToolbarBinding

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): AudioSettingFragmentBinding =
        AudioSettingFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        settingToolbarBinding = requireViewBinding().settingToolbar
        settingToolbarBinding.settingBackNav.setOnClickListener(this)

        requireViewBinding().apply {
            SettingRowViewWrapper(settingAudioBasic).apply {
                bindData(
                    SettingRowItem(
                        rowType = SettingRowType.ROW_SETTING_CHECKBOX,
                        settingId = SettingActionId.SETTING_AUDIO_QUALITY_BASIC,
                        title = getString(R.string.quality_basic), subTitle = getString(R.string.quality_basic_desc)
                    )
                ) {}
                setChecked(false)
            }
            SettingRowViewWrapper(settingAudioMedium).apply {
                bindData(
                    SettingRowItem(
                        rowType = SettingRowType.ROW_SETTING_CHECKBOX,
                        settingId = SettingActionId.SETTING_AUDIO_QUALITY_MEDIUM,
                        title = getString(R.string.quality_medium), subTitle = getString(R.string.quality_medium_desc)
                    )
                ) {

                }
                setChecked(false)
            }
            SettingRowViewWrapper(settingAudioHigh).apply {
                bindData(
                    SettingRowItem(
                        rowType = SettingRowType.ROW_SETTING_CHECKBOX,
                        settingId = SettingActionId.SETTING_AUDIO_QUALITY_HIGH,
                        title = getString(R.string.quality_high), subTitle = getString(R.string.quality_high_desc)
                    )
                ) {

                }
                setChecked(true)
            }

            SettingRowViewWrapper(settingDataSaver).apply {
                bindData(
                    SettingRowItem(
                        SettingRowType.ROW_SETTING_SWITCH,
                        SettingActionId.SETTING_DATA_SAVER,
                        title = getString(R.string.data_saving),
                        subTitle = getString(R.string.data_saving_desc)
                    )
                ) {

                }
                setChecked(true)
            }

            SettingRowViewWrapper(settingEqualizer).apply {
                bindData(
                    SettingRowItem(
                        SettingRowType.ROW_SETTING,
                        SettingActionId.SETTING_EQUALIZER,
                        title = getString(R.string.setting_equalizer),
                        subTitle = getString(R.string.setting_equalizer_desc)
                    )
                ) {}
                setChecked(true)
            }

            SettingRowViewWrapper(settingCrossFade).apply {
                bindData(
                    SettingRowItem(
                        SettingRowType.ROW_SETTING_SLIDER,
                        SettingActionId.SETTING_CROSS_FADE_SONG,
                        title = getString(R.string.cross_fade_song),
                        subTitle = getString(R.string.cross_fade_song_desc)
                    )
                ) {}
                setSlide(20f)
            }

            SettingRowViewWrapper(settingGapLess).apply {
                bindData(
                    SettingRowItem(
                        SettingRowType.ROW_SETTING_SWITCH,
                        SettingActionId.SETTING_GAP_LESS_SONG,
                        title = getString(R.string.gap_less_song),
                        subTitle = getString(R.string.gap_less_song_desc)
                    )
                ) {}
            }

            SettingRowViewWrapper(settingAutoMix).apply {
                bindData(
                    SettingRowItem(
                        SettingRowType.ROW_SETTING_SWITCH,
                        SettingActionId.SETTING_AUTO_MIX,
                        title = getString(R.string.auto_mix),
                        subTitle = getString(R.string.auto_mix_desc)
                    )
                ) {}
                setChecked(true)
            }

            SettingRowViewWrapper(settingNormalizeVolume).apply {
                bindData(
                    SettingRowItem(
                        SettingRowType.ROW_SETTING_SWITCH,
                        SettingActionId.SETTING_NORMALIZE_VOLUME,
                        title = getString(R.string.normalize_volume),
                        subTitle = getString(R.string.normalize_volume_desc)
                    )
                ) {}
                setChecked(true)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!) {
            settingToolbarBinding.settingBackNav -> {
                findNavController().navigateUp()
            }
        }
    }
}