package com.creative.spotifykt.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creative.spotifykt.data.model.local.MusicListUI
import com.creative.spotifykt.usecase.home.GetHomeLayoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeLayoutUseCase: GetHomeLayoutUseCase
) : ViewModel() {
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