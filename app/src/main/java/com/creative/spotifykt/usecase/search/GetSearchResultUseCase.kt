package com.creative.spotifykt.usecase.search

import com.creative.spotifykt.App
import com.creative.spotifykt.data.model.local.ListSearchResult
import com.creative.spotifykt.data.model.local.ListSearchTopic
import com.creative.spotifykt.data.model.local.SearchResult
import com.creative.spotifykt.ui.search.result.SearchResultState
import com.creative.spotifykt.usecase.BaseFlowUseCase
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by dan on 20/08/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

class GetSearchResultUseCase(
    private val app: App, private val moshi: Moshi
) : BaseFlowUseCase<GetSearchResultUseCase.Param, SearchResultState>() {
    data class Param(val keyword: String)

    override fun create(input: Param): Flow<SearchResultState> {
        return flow {
            val jsonAdapter = moshi.adapter(ListSearchResult::class.java)
            val topics: ListSearchResult? = kotlin.runCatching {
                jsonAdapter.fromJson(runCatching {
                    app.assets.open("search-results.json").let {
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
            val res = topics?.items?.filterNotNull() ?: listOf()
            if (res.isEmpty()) {
                emit(SearchResultState.Empty)
            } else {
                emit(SearchResultState.Success(res))
            }
        }.distinctUntilChanged().flowOn(Dispatchers.IO)
    }
}