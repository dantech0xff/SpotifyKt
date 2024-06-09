package com.creative.spotifykt.usecase.favorite

import android.content.Context
import com.creative.spotifykt.App
import com.creative.spotifykt.data.model.local.ListFavMusicTab
import com.creative.spotifykt.ui.favorite.TabLayoutState
import com.creative.spotifykt.usecase.BaseFlowUseCase
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by dan on 20/08/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

@ViewModelScoped
class GetTabLayoutUseCase @Inject constructor(
    @ApplicationContext private val app: Context,
    private val moshi: Moshi
) : BaseFlowUseCase<Unit, TabLayoutState>() {
    override fun create(input: Unit): Flow<TabLayoutState> {
        return flow {
            val jsonAdapter = moshi.adapter(ListFavMusicTab::class.java)
            val listFavMusicTab: ListFavMusicTab? = kotlin.runCatching {
                jsonAdapter.fromJson(runCatching {
                    app.assets.open("favorite-music-tab.json").let {
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
            val res = listFavMusicTab?.items?.filterNotNull() ?: listOf()
            if (res.isEmpty())
                emit(TabLayoutState.Empty)
            else {
                emit(TabLayoutState.Success(res))
            }
        }.flowOn(Dispatchers.IO)
    }
}