package com.creative.spotifykt.ui.setting.explicit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.databinding.ExplicitContentFragmentBinding
import com.creative.spotifykt.databinding.LayoutToolbarBinding
import com.creative.spotifykt.di.component.FragmentComponent

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
    }

    override fun onClick(v: View?) {
        when (v!!) {
            settingToolbarBinding.settingBackNav -> {
                findNavController().navigateUp()
            }
        }
    }
}