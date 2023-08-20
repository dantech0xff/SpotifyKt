package com.creative.spotifykt.di.getter

import com.creative.spotifykt.usecase.search.GetSearchResultUseCase

/**
 * Created by dan on 20/08/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

interface IUseCaseModule {
    val getSearchResultUseCase: GetSearchResultUseCase
}