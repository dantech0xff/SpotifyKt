package com.creative.spotifykt.ui.search.result

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.data.model.local.SearchResult
import com.creative.spotifykt.databinding.SearchResultFragmentBinding
import com.creative.spotifykt.di.component.FragmentComponent

class SearchResultFragment : BaseFragment<SearchResultFragmentBinding, SearchResultViewModel>() {

    private val searchResultAdapter: SearchResultListAdapter by lazy { SearchResultListAdapter() }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): SearchResultFragmentBinding =
        SearchResultFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel

            with(searchTextEdit) {
                addTextChangedListener {
                    viewModel.searchKeyword(it.toString())
                }
                if (requestFocus()) {
                    val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
                }
            }

            with(listSearchResult) {
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    orientation = LinearLayoutManager.VERTICAL
                }
                adapter = searchResultAdapter
            }
            searchBackNav.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.searchResult.observe(viewLifecycleOwner) {
            if(it is SearchResultState.Success) {
                searchResultAdapter.submitList(it.data)
            }
        }
    }
}