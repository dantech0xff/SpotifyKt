package com.creative.spotifykt.ui.setting.explicit

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
import com.creative.spotifykt.databinding.ExplicitContentFragmentBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.ui.IAppBarHandler
import com.creative.spotifykt.ui.ISettingRowHandler
import com.creative.spotifykt.ui.setting.SettingListAdapter

class ExplicitContentFragment : BaseFragment<ExplicitContentFragmentBinding, ExplicitContentViewModel>() {
    private val settingListAdapter: SettingListAdapter by lazy {
        SettingListAdapter(
            object : ISettingRowHandler {
                override fun onClick(settingRowUI: SettingRowUI) {}

                override fun onSwitch(settingRowUI: SettingRowUI, isChecked: Boolean) {}

                override fun onChecked(settingRowUI: SettingRowUI, isChecked: Boolean) {}

                override fun onSlider(settingRowUI: SettingRowUI, value: Float) {}
            }
        )
    }
    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): ExplicitContentFragmentBinding =
        ExplicitContentFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            settingToolbar.data = AppBarUI(title = TextLabel(getString(R.string.explicit_content), ColorStyle.PRIMARY.value))
            settingToolbar.appBarHandler = object : IAppBarHandler {
                override fun handleBack() {
                    findNavController().navigateUp()
                }
            }
            listExplicitSetting.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
            listExplicitSetting.adapter = settingListAdapter
        }
    }

}