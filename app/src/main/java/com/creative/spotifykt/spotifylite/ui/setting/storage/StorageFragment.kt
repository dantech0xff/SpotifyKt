package com.creative.spotifykt.spotifylite.ui.setting.storage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.creative.spotifykt.databinding.LayoutToolbarBinding
import com.creative.spotifykt.databinding.StorageFragmentBinding
import com.creative.spotifykt.spotifylite.di.component.FragmentComponent
import com.creative.spotifykt.base.ui.BaseFragment

class StorageFragment : BaseFragment<StorageFragmentBinding, StorageViewModel>(), View.OnClickListener {
    private lateinit var settingToolbar: LayoutToolbarBinding

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        StorageFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        settingToolbar = requireViewBinding().settingToolbar
        settingToolbar.settingBackNav.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!) {
            settingToolbar.settingBackNav -> {
                findNavController().navigateUp()
            }
        }
    }
}