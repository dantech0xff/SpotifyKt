package com.creative.spotifykt.ui.favorite.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.databinding.ListFavoriteFragmentBinding
import com.creative.spotifykt.di.component.FragmentComponent

class ListFavoriteFragment : BaseFragment<ListFavoriteFragmentBinding, ListFavoriteViewModel>() {

    private val favAdapter: ListFavAdapter by lazy { ListFavAdapter() }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): ListFavoriteFragmentBinding =
        ListFavoriteFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            listFavoriteItems.adapter = favAdapter
            listFavoriteItems.layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.listFavorite.observe(viewLifecycleOwner) {
            if (it is ListFavoriteState.Success) {
                favAdapter.submitList(it.data.mapIndexed { index, favoriteMusicRow ->
                    favoriteMusicRow.copy(headline = favoriteMusicRow.headline?.copy(
                        text = "${index + 1}. ${favoriteMusicRow.headline.text.orEmpty()}"
                    ))
                })
            }
        }
    }
}