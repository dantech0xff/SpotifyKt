package com.creative.spotifykt.ui.premium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.databinding.PremiumFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PremiumFragment : BaseFragment<PremiumFragmentBinding>() {

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): PremiumFragmentBinding =
        PremiumFragmentBinding.inflate(inflater, container, false)

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {}
    }
}