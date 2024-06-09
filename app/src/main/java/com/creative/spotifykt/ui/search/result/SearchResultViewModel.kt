package com.creative.spotifykt.ui.search.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creative.spotifykt.data.model.local.SearchResult
import com.creative.spotifykt.usecase.search.GetSearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase,
) : ViewModel() {

    private val searchResultLiveData: MutableLiveData<SearchResultState> = MutableLiveData()
    val searchResult: LiveData<SearchResultState> = searchResultLiveData

    init {
        searchResultLiveData.value = SearchResultState.Empty
    }

    fun searchKeyword(keyword: String) {
        viewModelScope.launch(Dispatchers.Main) {
            getSearchResultUseCase.execute(
                GetSearchResultUseCase.Param(keyword)
            ).collect {
                searchResultLiveData.value = it
            }
        }
    }
}

sealed class SearchResultState {
    object Loading : SearchResultState()
    data class Success(val data: List<SearchResult>) : SearchResultState()
    object Error : SearchResultState()
    object Empty : SearchResultState()
}