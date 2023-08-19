package com.creative.spotifykt.spotifylite.ui.setting.download

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.creative.spotifykt.databinding.DownloadSettingFragmentBinding
import com.creative.spotifykt.databinding.LayoutToolbarBinding
import com.creative.spotifykt.spotifylite.data.SettingActionId
import com.creative.spotifykt.spotifylite.data.SettingRowItem
import com.creative.spotifykt.spotifylite.data.SettingRowType
import com.creative.spotifykt.spotifylite.di.component.FragmentComponent
import com.creative.spotifykt.base.ui.BaseFragment
import com.creative.spotifykt.R
import com.creative.spotifykt.spotifylite.ui.setting.SettingRowViewWrapper

class DownloadSettingFragment : BaseFragment<DownloadSettingFragmentBinding, DownloadSettingViewModel>(), View.OnClickListener {
    private lateinit var settingToolbarBinding: LayoutToolbarBinding
    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): DownloadSettingFragmentBinding =
        DownloadSettingFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        requireViewBinding().apply {

            settingToolbarBinding = settingToolbar.apply {
                settingBackNav.setOnClickListener(this@DownloadSettingFragment)
            }

            SettingRowViewWrapper(downloadSettingRow).apply {
                bindData(
                    SettingRowItem(
                        SettingRowType.ROW_SETTING_SWITCH,
                        SettingActionId.SETTING_DOWNLOAD_CELLULAR,
                        title = getString(R.string.download_setting_title),
                        subTitle = getString(R.string.download_setting_subtitle)
                    )
                )
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