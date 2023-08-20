package com.creative.spotifykt.usecase.search

import com.creative.spotifykt.App
import com.creative.spotifykt.data.model.local.ListSearchTopic
import com.creative.spotifykt.data.model.local.SearchTopic
import com.creative.spotifykt.ui.search.ListTopicSearchState
import com.creative.spotifykt.usecase.BaseFlowUseCase
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

/**
 * Created by dan on 20/08/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

class GetSearchTopicUseCase(
    private val app: App, private val moshi: Moshi
) : BaseFlowUseCase<Unit, ListTopicSearchState>() {
    override fun create(input: Unit): Flow<ListTopicSearchState> {
        return flow {
            val jsonAdapter = moshi.adapter(ListSearchTopic::class.java)
            val topics: ListSearchTopic? = kotlin.runCatching {
                jsonAdapter.fromJson(runCatching {
                    app.assets.open("search-topic.json").let {
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

            emit(
                ListTopicSearchState.Success(
                    topics?.items?.filterNotNull() ?: listOf()
                )
            )
        }.flowOn(Dispatchers.IO)
    }
}