package com.creative.spotifykt.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.databinding.FragmentPlayerBinding
import com.creative.spotifykt.di.component.FragmentComponent

class PlayerFragment : BaseFragment<FragmentPlayerBinding, PlayerViewModel>() {

    companion object {
        fun newInstance() = PlayerFragment()
    }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPlayerBinding {
        return FragmentPlayerBinding.inflate(inflater, container, false)
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
    }
}