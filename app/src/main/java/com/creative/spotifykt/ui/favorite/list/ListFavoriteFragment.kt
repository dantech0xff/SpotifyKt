package com.creative.spotifykt.ui.favorite.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.data.model.local.FavMusicTab
import com.creative.spotifykt.databinding.ListFavoriteFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFavoriteFragment : BaseFragment<ListFavoriteFragmentBinding>() {

    private val viewModel: ListFavoriteViewModel by viewModels()

    private val favAdapter: ListFavAdapter by lazy { ListFavAdapter() }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): ListFavoriteFragmentBinding =
        ListFavoriteFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            listFavoriteItems.adapter = favAdapter
            listFavoriteItems.layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }
    }

    private fun setupObservers() {
        viewModel.listFavorite.observe(viewLifecycleOwner) {
            if (it is ListFavoriteState.Success) {
                favAdapter.submitList(it.data.mapIndexed { index, favoriteMusicRow ->
                    favoriteMusicRow.copy(
                        headline = favoriteMusicRow.headline?.copy(
                            text = "${index + 1}. ${favoriteMusicRow.headline.text.orEmpty()}"
                        )
                    )
                })
            }
        }
    }
}