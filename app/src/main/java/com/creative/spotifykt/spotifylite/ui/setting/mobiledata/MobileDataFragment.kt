package com.creative.spotifykt.spotifylite.ui.setting.mobiledata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.creative.spotifykt.databinding.LayoutToolbarBinding
import com.creative.spotifykt.databinding.MobileDataFragmentBinding
import com.creative.spotifykt.spotifylite.di.component.FragmentComponent
import com.creative.spotifykt.base.ui.BaseFragment

class MobileDataFragment : BaseFragment<MobileDataFragmentBinding, MobileDataViewModel>(), View.OnClickListener {

    lateinit var toolbarBinding: LayoutToolbarBinding

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        MobileDataFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        toolbarBinding = requireViewBinding().settingToolbar

        toolbarBinding.settingBackNav.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!) {
            toolbarBinding.settingBackNav -> {
                findNavController().popBackStack()
            }
        }
    }
}