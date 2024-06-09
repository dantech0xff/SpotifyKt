package com.creative.spotifykt.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creative.spotifykt.data.model.local.FavMusicTab
import com.creative.spotifykt.usecase.favorite.GetTabLayoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getTabLayoutUseCase: GetTabLayoutUseCase
) : ViewModel() {

    private val tabLayoutLiveData: MutableLiveData<TabLayoutState> = MutableLiveData<TabLayoutState>()
    val tabLayout: LiveData<TabLayoutState> = tabLayoutLiveData

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getTabLayoutUseCase.execute(Unit).collect {
                tabLayoutLiveData.value = it
            }
        }
    }
}

sealed class TabLayoutState {
    object Loading : TabLayoutState()
    object Empty : TabLayoutState()
    data class Success(val data: List<FavMusicTab>) : TabLayoutState()
    data class Error(val error: Throwable) : TabLayoutState()
}