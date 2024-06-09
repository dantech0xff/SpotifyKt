package com.creative.spotifykt.ui.topic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.databinding.FragmentMusicTopicBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by dan on 17/09/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

@AndroidEntryPoint
class MusicTopicFragment : BaseFragment<FragmentMusicTopicBinding>() {
    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMusicTopicBinding {
        return FragmentMusicTopicBinding.inflate(inflater, container, false)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {

        }
    }
}