package com.creative.spotifykt.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.creative.spotifykt.App
import com.creative.spotifykt.core.viewmodel.BaseViewModel
import com.creative.spotifykt.data.model.local.HomeLayoutUI
import com.creative.spotifykt.data.model.local.MusicListUI
import com.creative.spotifykt.usecase.home.GetHomeLayoutUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val appContext: App,
    private val getHomeLayoutUseCase: GetHomeLayoutUseCase
) : BaseViewModel() {
    private val homeListLiveData: MutableLiveData<HomeListState> = MutableLiveData()
    val homeList: LiveData<HomeListState> = homeListLiveData

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getHomeLayoutUseCase.execute(Unit)
                .collect {
                    homeListLiveData.value = (it)
                }
        }
    }
}

sealed class HomeListState {
    object Loading : HomeListState()
    object Empty: HomeListState()
    data class Success(val data: List<MusicListUI>) : HomeListState()
    object Error : HomeListState()
}