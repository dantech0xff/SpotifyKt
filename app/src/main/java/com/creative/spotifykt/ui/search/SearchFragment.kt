package com.creative.spotifykt.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.R
import com.creative.spotifykt.data.model.local.MusicTopic
import com.creative.spotifykt.databinding.SearchFragmentBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.core.ui.BaseFragment

class SearchFragment : BaseFragment<SearchFragmentBinding, SearchViewModel>(), View.OnClickListener {
    private lateinit var listSearchTopic: RecyclerView
    private val listSearchAdapter: SearchListAdapter by lazy { SearchListAdapter() }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): SearchFragmentBinding =
        SearchFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        requireViewBinding().apply {
            layoutSearchBar.setOnClickListener(this@SearchFragment)
        }
        listSearchTopic = requireViewBinding().listSearchTopic
        setupListSearch()
    }

    private fun setupListSearch() {
        val listDumData = mutableListOf<MusicTopic>()
        listDumData.add(MusicTopic(name = "Podcasts"))
        listDumData.add(MusicTopic(name = "Vietnamese Music"))
        listDumData.add(MusicTopic(name = "K-Pop"))
        listDumData.add(MusicTopic(name = "Pop"))
        listDumData.add(MusicTopic(name = "EQUAL"))
        listDumData.add(MusicTopic(name = "Chill"))
        listDumData.add(MusicTopic(name = "Romance"))
        listDumData.add(MusicTopic(name = "Hip Hop"))
        listDumData.add(MusicTopic(name = "RADAR"))
        listDumData.add(MusicTopic(name = "Dance/Electronic"))
        listDumData.add(MusicTopic(name = "Programmer"))
        listDumData.add(MusicTopic(name = "Indie"))
        listDumData.add(MusicTopic(name = "Alternative"))
        listDumData.add(MusicTopic(name = "Covid-19"))
        listDumData.add(MusicTopic(name = "At Home"))
        listDumData.add(MusicTopic(name = "Have Sex"))
        listDumData.add(MusicTopic(name = "Instrumental"))
        listDumData.add(MusicTopic(name = "Workout"))
        listDumData.add(MusicTopic(name = "Dota 2"))
        listDumData.add(MusicTopic(name = "Blockchain"))
        listDumData.add(MusicTopic(name = "Cryptocurrency"))
        listDumData.add(MusicTopic(name = "Wellness"))
        listDumData.add(MusicTopic(name = "Rock"))
        listDumData.add(MusicTopic(name = "Jazz"))
        listDumData.add(MusicTopic(name = "Classical"))
        listDumData.add(MusicTopic(name = "Travel"))
        listDumData.add(MusicTopic(name = "Kids & Family"))
        listDumData.add(MusicTopic(name = "..."))

        listSearchTopic.apply {
            layoutManager = GridLayoutManager(requireContext(), 2).apply {
                orientation = GridLayoutManager.VERTICAL
            }
            adapter = listSearchAdapter.apply {
                submitList(listDumData.toList())
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!) {
            requireViewBinding().layoutSearchBar -> {
                findNavController().navigate(R.id.action_navigation_search_to_searchResultFragment)
            }
        }
    }
}