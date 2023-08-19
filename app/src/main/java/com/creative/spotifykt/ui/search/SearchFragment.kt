package com.creative.spotifykt.ui.search

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.creative.spotifykt.R
import com.creative.spotifykt.core.log
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.databinding.SearchFragmentBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.ui.IDeeplinkHandler

class SearchFragment : BaseFragment<SearchFragmentBinding, SearchViewModel>(), IDeeplinkHandler {
    private val listSearchAdapter: SearchListAdapter by lazy {
        SearchListAdapter(this@SearchFragment)
    }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): SearchFragmentBinding =
        SearchFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            deeplinkHandler = this@SearchFragment
            layoutSearchBar.setOnClickListener {
                findNavController().navigate(R.id.action_navigation_search_to_searchResultFragment)
            }

            listSearchTopic.apply {
                val spanCount = 2
                layoutManager = GridLayoutManager(context, spanCount).apply {
                    orientation = GridLayoutManager.VERTICAL
                }
                adapter = listSearchAdapter
                addItemDecoration(object : ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        super.getItemOffsets(outRect, view, parent, state)
                        val index = parent.getChildAdapterPosition(view)
                        if (index % spanCount == 0) {
                            outRect.left = resources.getDimensionPixelSize(R.dimen.xds_space_l)
                            outRect.right = resources.getDimensionPixelSize(R.dimen.xds_space_m) / 2
                        } else if (index % spanCount == (spanCount - 1)) {
                            outRect.left = resources.getDimensionPixelSize(R.dimen.xds_space_m) / 2
                            outRect.right = resources.getDimensionPixelSize(R.dimen.xds_space_l)
                        } else {
                            outRect.left = resources.getDimensionPixelSize(R.dimen.xds_space_m) / 2
                            outRect.right = resources.getDimensionPixelSize(R.dimen.xds_space_m) / 2
                        }
                    }
                })
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.listSearch.observe(viewLifecycleOwner) {
            when (it) {
                is SearchListState.Loading -> {
                    log("Loading")
                }

                is SearchListState.Success -> {
                    log("Success")
                    listSearchAdapter.submitList(it.data)
                }

                is SearchListState.Error -> {
                    log("Error")
                }
            }
        }
    }

    override fun handleDeeplink(deeplink: String?) {
        log("handleDeeplink: $deeplink")
    }
}