package com.creative.spotifykt.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.creative.spotifykt.core.viewmodel.BaseViewModel
import com.creative.spotifykt.data.model.local.SearchTopic
import com.creative.spotifykt.usecase.search.GetSearchResultUseCase
import com.creative.spotifykt.usecase.search.GetSearchTopicUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getSearchTopicUseCase: GetSearchTopicUseCase
) : BaseViewModel() {
    private val listSearchLiveData: MutableLiveData<ListTopicSearchState> = MutableLiveData()
    val listSearch: LiveData<ListTopicSearchState> = listSearchLiveData

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getSearchTopicUseCase.execute(Unit).collect {
                listSearchLiveData.value = it
            }
        }
    }
}

sealed class ListTopicSearchState {
    object Loading : ListTopicSearchState()
    data class Success(val data: List<SearchTopic>) : ListTopicSearchState()
    object Error : ListTopicSearchState()
}