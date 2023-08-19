package com.creative.spotifykt.spotifylite.ui.search.result

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.spotifylite.data.MusicSquareUI
import com.creative.spotifykt.spotifylite.data.SearchResultData
import com.creative.spotifykt.spotifylite.data.SearchResultType
import com.creative.spotifykt.databinding.SearchResultFragmentBinding
import com.creative.spotifykt.spotifylite.di.component.FragmentComponent
import com.creative.spotifykt.base.ui.BaseFragment
import com.creative.spotifykt.spotifylite.data.TextLabel
import com.creative.spotifykt.spotifylite.ui.home.HomeFragment

class SearchResultFragment : BaseFragment<SearchResultFragmentBinding, SearchResultViewModel>(), View.OnClickListener {

    private lateinit var searchTextEdit: AppCompatEditText
    private lateinit var listSearchResult: RecyclerView

    private val searchResultAdapter: SearchResultListAdapter by lazy { SearchResultListAdapter() }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): SearchResultFragmentBinding =
        SearchResultFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        searchTextEdit = requireViewBinding().searchTextEdit
        listSearchResult = requireViewBinding().listSearchResult

        with(searchTextEdit) {
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {

                }
            })
            if (requestFocus()) {
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
            }
        }

        with(listSearchResult) {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = searchResultAdapter.apply {
                submitList(listOfSearchResult())
            }
        }
        requireViewBinding().searchBackNav.setOnClickListener(this)
    }

    private fun listOfSearchResult() : List<SearchResultData> {
        val listSearchResult = mutableListOf<SearchResultData>().apply {
            add(
                SearchResultData(
                    SearchResultType.MUSIC,
                    title = "Alan Walker", subTitle = "Artist"
                )
            )
            add(
                SearchResultData(
                    SearchResultType.FEATURE,
                    title = "Featuring Alan Walker",
                    featureList = mutableListOf<MusicSquareUI>().apply {
                        for (i in 0..5) {
                            add(
                                MusicSquareUI(
                                    id = i.toString(),
                                    image = HomeFragment.DUMMY_URL,
                                    headline = TextLabel("Spotify Lite", "PRIMARY", "PRIMARY"   )
                                )
                            )
                        }
                    })
            )
            add(
                SearchResultData(
                    SearchResultType.MUSIC,
                    title = "This is Alan Walker", subTitle = "PLaylist"
                )
            )
            add(
                SearchResultData(
                    SearchResultType.MUSIC,
                    title = "Alone", subTitle = "Song - Alan Walker"
                )
            )
            add(
                SearchResultData(
                    SearchResultType.MUSIC,
                    title = "Different World", subTitle = "Album - Alan Walker"
                )
            )
            add(
                SearchResultData(
                    SearchResultType.MUSIC,
                    title = "All Falls Down (feat. Batman)", subTitle = "Album - Alan Walker, Batman"
                )
            )
            add(
                SearchResultData(
                    SearchResultType.MUSIC,
                    title = "Alone, Pt. II", subTitle = "Album - Alan Walker, Ava Max"
                )
            )
            add(
                SearchResultData(
                    SearchResultType.MUSIC,
                    title = "Trap", subTitle = "Sing - Bitcoin"
                )
            )

            add(
                SearchResultData(
                    SearchResultType.ADDITIONAL,
                    title = "See all artist"
                )
            )
            add(
                SearchResultData(
                    SearchResultType.ADDITIONAL,
                    title = "See all song"
                )
            )
            add(
                SearchResultData(
                    SearchResultType.ADDITIONAL,
                    title = "See all playlist"
                )
            )
            add(
                SearchResultData(
                    SearchResultType.ADDITIONAL,
                    title = "See all albums"
                )
            )
            add(
                SearchResultData(
                    SearchResultType.ADDITIONAL,
                    title = "See all genres & moods"
                )
            )
            add(
                SearchResultData(
                    SearchResultType.ADDITIONAL,
                    title = "See all podcast"
                )
            )
            add(
                SearchResultData(
                    SearchResultType.ADDITIONAL,
                    title = "See all podcast episodes"
                )
            )
        }

        return listSearchResult.toList()
    }

    override fun onClick(v: View?) {
        when (v!!) {
            requireViewBinding().searchBackNav -> {
                findNavController().navigateUp()
            }
        }
    }

    // search result row type
    /*
    * Row artist
    * Row feature
    * Row music
    * Row additional
    * */
    // row data
    /*
    * Row id
    * title, subtitle
    * url image
    * xxx data to direct
    * */
}