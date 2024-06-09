package com.creative.spotifykt.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.databinding.FragmentPlayerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerFragment : BaseFragment<FragmentPlayerBinding>() {

    companion object {
        fun newInstance() = PlayerFragment()
    }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPlayerBinding {
        return FragmentPlayerBinding.inflate(inflater, container, false)
    }

    override fun setupView(savedInstanceState: Bundle?) {
    }
}