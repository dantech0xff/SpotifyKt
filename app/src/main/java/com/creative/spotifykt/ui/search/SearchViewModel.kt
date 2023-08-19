package com.creative.spotifykt.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.creative.spotifykt.core.viewmodel.BaseViewModel
import com.creative.spotifykt.data.model.local.MusicSearchTopic

class SearchViewModel : BaseViewModel() {
    private val listSearchLiveData: MutableLiveData<SearchListState> = MutableLiveData()
    val listSearch: LiveData<SearchListState> = listSearchLiveData

    init {
        val listDumData = mutableListOf<MusicSearchTopic>()
        listDumData.add(MusicSearchTopic(title = "Podcasts"))
        listDumData.add(MusicSearchTopic(title = "Vietnamese Music"))
        listDumData.add(MusicSearchTopic(title = "K-Pop"))
        listDumData.add(MusicSearchTopic(title = "Pop"))
        listDumData.add(MusicSearchTopic(title = "EQUAL"))
        listDumData.add(MusicSearchTopic(title = "Chill"))
        listDumData.add(MusicSearchTopic(title = "Romance"))
        listDumData.add(MusicSearchTopic(title = "Hip Hop"))
        listDumData.add(MusicSearchTopic(title = "RADAR"))
        listDumData.add(MusicSearchTopic(title = "Dance/Electronic"))
        listDumData.add(MusicSearchTopic(title = "Programmer"))
        listDumData.add(MusicSearchTopic(title = "Indie"))
        listDumData.add(MusicSearchTopic(title = "Alternative"))
        listDumData.add(MusicSearchTopic(title = "Covid-19"))
        listDumData.add(MusicSearchTopic(title = "At Home"))
        listDumData.add(MusicSearchTopic(title = "Have Sex"))
        listDumData.add(MusicSearchTopic(title = "Instrumental"))
        listDumData.add(MusicSearchTopic(title = "Workout"))
        listDumData.add(MusicSearchTopic(title = "Dota 2"))
        listDumData.add(MusicSearchTopic(title = "Blockchain"))
        listDumData.add(MusicSearchTopic(title = "Cryptocurrency"))
        listDumData.add(MusicSearchTopic(title = "Wellness"))
        listDumData.add(MusicSearchTopic(title = "Rock"))
        listDumData.add(MusicSearchTopic(title = "Jazz"))
        listDumData.add(MusicSearchTopic(title = "Classical"))
        listDumData.add(MusicSearchTopic(title = "Travel"))
        listDumData.add(MusicSearchTopic(title = "Kids & Family"))
        listDumData.add(MusicSearchTopic(title = "..."))
        listSearchLiveData.value = SearchListState.Success(listDumData)
    }
}

sealed class SearchListState {
    object Loading : SearchListState()
    data class Success(val data: List<MusicSearchTopic>) : SearchListState()
    object Error : SearchListState()
}