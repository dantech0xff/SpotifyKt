package com.creative.spotifykt.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.creative.spotifykt.R
import com.creative.spotifykt.databinding.FavoriteFragmentBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.ui.favorite.list.ListFavoriteFragment
import com.creative.spotifykt.core.ui.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FavoriteFragment : BaseFragment<FavoriteFragmentBinding, FavoriteViewModel>() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    private val adapter: ScreenSlidePagerAdapter by lazy { ScreenSlidePagerAdapter(requireActivity()) }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): FavoriteFragmentBinding =
        FavoriteFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    private val listTitle by lazy {
        listOf(
            getString(R.string.playlists),
            getString(R.string.artists),
            getString(R.string.albums),
            getString(R.string.podcasts)
        )
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewPager = viewBinding!!.viewPager
        tabLayout = viewBinding!!.tabLayout
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = listTitle[position % listTitle.size]
        }.attach()
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = FAVORITE_TAB_COUNT

        override fun createFragment(position: Int): Fragment {
            return ListFavoriteFragment().apply {
                arguments = Bundle().apply {
                    putString("tab_position", listTitle[position % listTitle.size])
                }
            }
        }
    }

    companion object {
        const val FAVORITE_TAB_COUNT = 4
    }
}