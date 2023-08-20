package com.creative.spotifykt.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.data.model.local.FavMusicTab
import com.creative.spotifykt.databinding.FavoriteFragmentBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.ui.favorite.list.ListFavoriteFragment
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Created by dan on 20/08/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

class FavoriteFragment : BaseFragment<FavoriteFragmentBinding, FavoriteViewModel>() {

    private val pagerAdapter: ScreenSlidePagerAdapter by lazy {
        ScreenSlidePagerAdapter(requireActivity())
    }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): FavoriteFragmentBinding =
        FavoriteFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            viewPager.adapter = pagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = (viewModel.tabLayout.value as? TabLayoutState.Success)?.data?.getOrNull(position)?.title.orEmpty()
            }.attach()
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.tabLayout.observe(viewLifecycleOwner) {
            if (it is TabLayoutState.Success) {
                pagerAdapter.updateList(it.data)
            }
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(
        fa: FragmentActivity
    ) : FragmentStateAdapter(fa) {

        private var listTabs: List<FavMusicTab> = listOf()

        fun updateList(list: List<FavMusicTab>) {
            listTabs = list
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int = listTabs.size

        override fun createFragment(position: Int): Fragment {
            return ListFavoriteFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(FavMusicTab::class.java.name, listTabs.getOrNull(position))
                }
            }
        }
    }
}