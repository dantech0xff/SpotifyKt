package com.creative.spotifykt.data.repo.search

import com.creative.spotifykt.data.model.local.SearchResult

/**
 * Created by dan on 20/08/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

class SearchRepoImpl : SearchRepo {
    override fun getSearchResults(keyword: String): List<SearchResult> {
        return listOf()
    }
}