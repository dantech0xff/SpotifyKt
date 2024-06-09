package com.creative.spotifykt.ui.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.creative.spotifykt.core.log
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.data.model.local.FavMusicTab
import com.creative.spotifykt.databinding.FavoriteFragmentBinding
import com.creative.spotifykt.ui.favorite.list.ListFavoriteFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by dan on 20/08/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FavoriteFragmentBinding>() {

    private val viewModel: FavoriteViewModel by viewModels()

    private val pagerAdapter: ScreenSlidePagerAdapter by lazy {
        ScreenSlidePagerAdapter(this)
    }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): FavoriteFragmentBinding =
        FavoriteFragmentBinding.inflate(inflater, container, false)

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            viewPager.adapter = pagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = (viewModel.tabLayout.value as? TabLayoutState.Success)?.data?.getOrNull(position)?.title.orEmpty()
            }.attach()
        }
        log("FavoriteFragment", "setupView")
    }

    override fun onStart() {
        super.onStart()
        log("FavoriteFragment", "onStart")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private  fun setupObservers() {
        viewModel.tabLayout.observe(viewLifecycleOwner) {
            log("FavoriteFragment", "tabLayout: $it")
            pagerAdapter.notifyDataSetChanged()
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(
        f: Fragment
    ) : FragmentStateAdapter(f) {

        private fun listTabs(): MutableList<FavMusicTab> = viewModel.tabLayout.value?.let {
            (it as? TabLayoutState.Success)?.data.orEmpty().toMutableList()
        } ?: mutableListOf()

        override fun getItemCount(): Int = listTabs().size

        override fun createFragment(position: Int): Fragment {
            return ListFavoriteFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(FavMusicTab::class.java.name, listTabs().getOrNull(position))
                }
            }
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        log("FavoriteFragment", "onViewStateRestored")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        log("FavoriteFragment", "onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        log("FavoriteFragment", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("FavoriteFragment", "onDestroy")
    }
}