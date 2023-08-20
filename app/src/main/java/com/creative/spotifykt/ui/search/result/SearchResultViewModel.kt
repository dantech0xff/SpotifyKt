package com.creative.spotifykt.ui.search.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.creative.spotifykt.core.viewmodel.BaseViewModel
import com.creative.spotifykt.data.model.local.SearchResult
import com.creative.spotifykt.usecase.search.GetSearchResultUseCase

class SearchResultViewModel(
    private val getSearchResultUseCase: GetSearchResultUseCase,
) : BaseViewModel() {

    private val searchResultLiveData: MutableLiveData<SearchResultState> = MutableLiveData()
    val searchResult: LiveData<SearchResultState> = searchResultLiveData

    init {
        searchResultLiveData.value = SearchResultState.Empty
    }

    fun searchKeyword(keyword: String) {

    }
}

sealed class SearchResultState {
    object Loading : SearchResultState()
    data class Success(val data: List<SearchResult>) : SearchResultState()
    object Error : SearchResultState()
    object Empty : SearchResultState()
}