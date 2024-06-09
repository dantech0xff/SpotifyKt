package com.creative.spotifykt.ui.setting.account

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
import com.creative.spotifykt.databinding.AccountSettingFragmentBinding
import com.creative.spotifykt.ui.IAppBarHandler
import com.creative.spotifykt.ui.ISettingRowHandler
import com.creative.spotifykt.ui.setting.SettingListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountSettingFragment : BaseFragment<AccountSettingFragmentBinding>() {

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

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): AccountSettingFragmentBinding =
        AccountSettingFragmentBinding.inflate(inflater, container, false)

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            settingToolbar.data = AppBarUI(title = TextLabel(getString(R.string.account), ColorStyle.PRIMARY.value))
            settingToolbar.appBarHandler = object : IAppBarHandler {
                override fun handleBack() {
                    findNavController().navigateUp()
                }
            }
            listAccountSettings.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
            listAccountSettings.adapter = settingListAdapter
        }
    }
}