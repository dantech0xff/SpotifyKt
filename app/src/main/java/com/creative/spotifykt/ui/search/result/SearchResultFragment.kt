package com.creative.spotifykt.ui.search.result

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.creative.spotifykt.core.log
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.databinding.SearchResultFragmentBinding
import com.creative.spotifykt.ui.IDeeplinkHandler

class SearchResultFragment : BaseFragment<SearchResultFragmentBinding>() {
    private val viewModel: SearchResultViewModel by viewModels()

    private val searchResultAdapter: SearchResultListAdapter by lazy {
        SearchResultListAdapter(deeplinkHandler)
    }

    private val deeplinkHandler: IDeeplinkHandler by lazy {
        object : IDeeplinkHandler {

            override fun handleDeeplink(deeplink: String?) {
                log("handleDeeplink: $deeplink")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): SearchResultFragmentBinding =
        SearchResultFragmentBinding.inflate(inflater, container, false)

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

    private fun setupObservers() {
        viewModel.searchResult.observe(viewLifecycleOwner) {
            if(it is SearchResultState.Success) {
                searchResultAdapter.submitList(it.data)
            }
        }
    }
}