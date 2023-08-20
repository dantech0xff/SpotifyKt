package com.creative.spotifykt.ui.favorite.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.creative.spotifykt.core.viewmodel.BaseViewModel
import com.creative.spotifykt.data.model.local.FavMusicTab
import com.creative.spotifykt.data.model.local.FavoriteMusicRow
import com.creative.spotifykt.usecase.favorite.GetFavoriteListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListFavoriteViewModel(
    private val favMusicTab: FavMusicTab?,
    private val getFavoriteListUseCase: GetFavoriteListUseCase
) : BaseViewModel() {

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