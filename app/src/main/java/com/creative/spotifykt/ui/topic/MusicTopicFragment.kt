package com.creative.spotifykt.ui.topic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.data.model.local.MusicListUI
import com.creative.spotifykt.databinding.FragmentMusicTopicBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.ui.view.SquareMusicListLayout

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

    override fun setupObservers() {
        super.setupObservers()
    }

    fun bindListSuggestion(listSuggestData: List<MusicListUI>) {
        viewBinding?.apply {
            listSuggestion.removeAllViews()
            listSuggestData.forEach {
                val squareMusicListLayout = SquareMusicListLayout(layoutInflater, handleDeeplink = null)
                squareMusicListLayout.bind(it, viewLifecycleOwner)
                listSuggestion.addView(
                    squareMusicListLayout.root, LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                )
            }
        }
    }
}