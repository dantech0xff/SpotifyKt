package com.creative.spotifykt.usecase.search

import com.creative.spotifykt.ui.search.result.SearchResultState
import com.creative.spotifykt.usecase.BaseFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by dan on 20/08/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

class GetSearchResultUseCase : BaseFlowUseCase<GetSearchResultUseCase.Param, SearchResultState>() {
    data class Param(val keyword: String)

    override fun create(input: Param): Flow<SearchResultState> {
        return flow {

        }
    }
}