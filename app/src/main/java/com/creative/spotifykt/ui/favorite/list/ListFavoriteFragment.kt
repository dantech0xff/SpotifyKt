package com.creative.spotifykt.ui.favorite.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.databinding.ListFavoriteFragmentBinding
import com.creative.spotifykt.data.model.local.FavMusicData
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.core.toast

class ListFavoriteFragment : BaseFragment<ListFavoriteFragmentBinding, ListFavoriteViewModel>() {

    private lateinit var recyclerViewFavItems: RecyclerView
    private val favAdapter: ListFavAdapter by lazy { ListFavAdapter() }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): ListFavoriteFragmentBinding =
        ListFavoriteFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        recyclerViewFavItems = requireViewBinding().listFavoriteItems
        val viewModelKey = arguments?.getString("tab_position", "") as String

        recyclerViewFavItems.adapter = favAdapter
        favAdapter.setOnItemClickListener { (item) ->
            activity?.toast(item)
        }
        recyclerViewFavItems.layoutManager = LinearLayoutManager(requireContext()).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        val listItem = mutableListOf<FavMusicData>()
        for (i in 1..100) {
            listItem.add(
                FavMusicData(
                    id = "$i $viewModelKey",
                    title = "$viewModelKey Title $i", subTitle = if (i < 100 / 1.5) "$viewModelKey Sub Title $i" else ""
                )
            )
        }
        favAdapter.differ.submitList(listItem)
    }
}