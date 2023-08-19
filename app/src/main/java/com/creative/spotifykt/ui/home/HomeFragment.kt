package com.creative.spotifykt.ui.home

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.creative.spotifykt.R
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.core.toast
import com.creative.spotifykt.databinding.FragmentHomeBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.ui.IDeeplinkHandler

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val homeAdapter: HomeListAdapter by lazy {
        HomeListAdapter(
            object : IDeeplinkHandler {
                override fun handleDeeplink(deeplink: String?) {
                    activity?.toast(deeplink.orEmpty())
                }
            }
        )
    }

    private val homeRecyclerPool = RecyclerView.RecycledViewPool()

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            homeRecyclerView.apply {
                adapter = homeAdapter
                layoutManager = LinearLayoutManager(this@HomeFragment.requireContext()).apply {
                    orientation = LinearLayoutManager.VERTICAL
                    recycleChildrenOnDetach = true
                }
                setRecycledViewPool(homeRecyclerPool)
                addItemDecoration(object : ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        super.getItemOffsets(outRect, view, parent, state)
                        val index = parent.getChildAdapterPosition(view)
                        val total = parent.adapter?.itemCount ?: 0
                        if (index == total - 1) {
                            outRect.bottom = resources.getDimensionPixelSize(R.dimen.xds_space_xxl)
                        } else {
                            outRect.bottom = resources.getDimensionPixelSize(R.dimen.xds_space_m)
                        }
                    }
                })
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.homeList.observe(viewLifecycleOwner) {
            when (it) {
                is HomeListState.Loading -> {
                    // show loading
                }

                is HomeListState.Error -> {
                    // show error
                }

                is HomeListState.Success -> {
                    // show success
                    homeAdapter.submitList(it.data)
                }
            }
        }
    }

    override fun shouldInterceptBackPress(): Boolean = false

    companion object {
        const val DUMMY_URL =
            "https://storage.googleapis.com/pr-newsroom-wp/1/2018/11/folder_920_201707260845-1.png"
    }
}