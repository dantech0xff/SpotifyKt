package com.creative.spotifykt.ui.topic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.data.model.local.MusicListUI
import com.creative.spotifykt.databinding.FragmentMusicTopicBinding
import com.creative.spotifykt.di.component.FragmentComponent

/**
 * Created by dan on 17/09/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

class MusicTopicFragment : BaseFragment<FragmentMusicTopicBinding, MusicTopicViewModel>() {
    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMusicTopicBinding {
        return FragmentMusicTopicBinding.inflate(inflater, container, false)
    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {

        }
    }
}