package com.creative.spotifykt.spotifylite.ui.setting.explicit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.creative.spotifykt.spotifylite.data.SettingActionId
import com.creative.spotifykt.spotifylite.data.SettingRowItem
import com.creative.spotifykt.spotifylite.data.SettingRowType
import com.creative.spotifykt.databinding.ExplicitContentFragmentBinding
import com.creative.spotifykt.databinding.LayoutToolbarBinding
import com.creative.spotifykt.spotifylite.di.component.FragmentComponent
import com.creative.spotifykt.base.ui.BaseFragment
import com.creative.spotifykt.spotifylite.ui.setting.SettingRowViewWrapper

class ExplicitContentFragment : BaseFragment<ExplicitContentFragmentBinding, ExplicitContentViewModel>(), View.OnClickListener {
    private lateinit var settingToolbarBinding: LayoutToolbarBinding
    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): ExplicitContentFragmentBinding =
        ExplicitContentFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        settingToolbarBinding = requireViewBinding().settingToolbar
        settingToolbarBinding.settingBackNav.setOnClickListener(this)

        requireViewBinding().apply {
            SettingRowViewWrapper(downloadSettingsRow).apply {
                bindData(
                    SettingRowItem(
                        SettingRowType.ROW_SETTING_SWITCH,
                        SettingActionId.SETTING_EXPLICIT_CONTENT,
                        title = "Allow explicit content",
                        subTitle = "Turn off to skip explicit content\nExplicit content is labeled with \uD83C\uDD74"
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