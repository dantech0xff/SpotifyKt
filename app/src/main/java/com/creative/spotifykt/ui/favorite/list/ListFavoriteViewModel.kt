package com.creative.spotifykt.ui.favorite.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creative.spotifykt.data.model.local.FavMusicTab
import com.creative.spotifykt.data.model.local.FavoriteMusicRow
import com.creative.spotifykt.usecase.favorite.GetFavoriteListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListFavoriteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getFavoriteListUseCase: GetFavoriteListUseCase
) : ViewModel() {

    private val favMusicTab: FavMusicTab? = savedStateHandle.get<FavMusicTab>(FavMusicTab::class.java.name)

    private val listFavoriteLiveData: MutableLiveData<ListFavoriteState> = MutableLiveData<ListFavoriteState>()
    val listFavorite: LiveData<ListFavoriteState> = listFavoriteLiveData

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getFavoriteListUseCase.execute(
                GetFavoriteListUseCase.Params(
                    favMusicTab?.id.orEmpty()
                )
            ).collect {
                listFavoriteLiveData.value = it
            }
        }
    }
}

sealed class ListFavoriteState {
    object Loading : ListFavoriteState()
    object Empty : ListFavoriteState()
    data class Success(val data: List<FavoriteMusicRow>) : ListFavoriteState()
    data class Error(val error: Throwable) : ListFavoriteState()
}