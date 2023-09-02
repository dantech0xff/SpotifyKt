package com.creative.spotifykt.ui.setting.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.databinding.AccountSettingFragmentBinding
import com.creative.spotifykt.databinding.LayoutToolbarBinding
import com.creative.spotifykt.di.component.FragmentComponent

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
    }

    override fun onClick(v: View?) {
        when (v!!) {
            settingToolbarBinding.settingBackNav -> {
                findNavController().navigateUp()
            }
        }
    }
}