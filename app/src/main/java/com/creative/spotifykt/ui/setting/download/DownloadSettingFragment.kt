package com.creative.spotifykt.ui.setting.download

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.databinding.DownloadSettingFragmentBinding
import com.creative.spotifykt.databinding.LayoutToolbarBinding
import com.creative.spotifykt.di.component.FragmentComponent

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