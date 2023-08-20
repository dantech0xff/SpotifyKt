package com.creative.spotifykt.usecase.favorite

import com.creative.spotifykt.App
import com.creative.spotifykt.data.model.local.ListFavoriteMusicRow
import com.creative.spotifykt.ui.favorite.list.ListFavoriteState
import com.creative.spotifykt.usecase.BaseFlowUseCase
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by dan on 20/08/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

class GetFavoriteListUseCase(
    private val app: App,
    private val moshi: Moshi
) : BaseFlowUseCase<GetFavoriteListUseCase.Params, ListFavoriteState>() {
    data class Params(val favoriteId: String)

    override fun create(input: Params): Flow<ListFavoriteState> {
        return flow {
            val jsonAdapter = moshi.adapter(ListFavoriteMusicRow::class.java)
            val list: ListFavoriteMusicRow? = kotlin.runCatching {

                jsonAdapter.fromJson(runCatching {
                    app.assets.open("favorite-list-music-type.json").let {
                        val size = it.available()
                        val buffer = ByteArray(size)
                        it.read(buffer)
                        it.close()
                        String(buffer, Charsets.UTF_8)
                    }
                }.onFailure {
                    com.creative.spotifykt.core.log(it.message ?: "Error")
                }.getOrDefault(""))
            }.onFailure {
                com.creative.spotifykt.core.log(it.message ?: "Error")
            }.getOrDefault(null)

            val res = list?.items?.filterNotNull() ?: listOf()
            if (res.isEmpty()) {
                emit(ListFavoriteState.Empty)
            } else {
                emit(ListFavoriteState.Success(res))
            }
        }.flowOn(Dispatchers.IO)
    }
}