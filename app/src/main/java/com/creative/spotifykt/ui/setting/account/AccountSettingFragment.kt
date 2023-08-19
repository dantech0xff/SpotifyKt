package com.creative.spotifykt.ui.setting.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.creative.spotifykt.data.model.local.SettingRowItem
import com.creative.spotifykt.data.model.local.SettingRowType
import com.creative.spotifykt.databinding.AccountSettingFragmentBinding
import com.creative.spotifykt.databinding.LayoutToolbarBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.ui.setting.SettingRowViewWrapper

class AccountSettingFragment : BaseFragment<AccountSettingFragmentBinding, AccountSettingViewModel>(), View.OnClickListener {
    private lateinit var settingToolbarBinding: LayoutToolbarBinding

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): AccountSettingFragmentBinding =
        AccountSettingFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        settingToolbarBinding = requireViewBinding().settingToolbar
        settingToolbarBinding.apply {
            settingBackNav.setOnClickListener(this@AccountSettingFragment)
        }

        requireViewBinding().apply {
            SettingRowViewWrapper(userNameRow).apply {
                bindData(
                    SettingRowItem(
                        SettingRowType.ROW_SETTING, "",
                        title = "Username",
                        subTitle = "abc zyx"
                    )
                )
            }
            SettingRowViewWrapper(emailRow).apply {
                bindData(
                    SettingRowItem(
                        SettingRowType.ROW_SETTING, "",
                        title = "E-mail",
                        subTitle = "CIA@FBI.USA"
                    )
                )
            }
            SettingRowViewWrapper(accountRow).apply {
                bindData(
                    SettingRowItem(
                        SettingRowType.ROW_SETTING, "",
                        title = "Account",
                        subTitle = "Free"
                    )
                )
            }
            SettingRowViewWrapper(upgradePremiumRow).apply {
                bindData(
                    SettingRowItem(
                        SettingRowType.ROW_SETTING, "",
                        title = "Upgrade to Spotify Premium",
                        subTitle = "Upgrade to premium to listen to music ad-free with unlimited skips"
                    )
                )
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