package com.creative.spotifykt.di.module.biz

import com.creative.spotifykt.usecase.search.GetSearchResultUseCase
import dagger.Module
import dagger.Provides

/**
 * Created by dan on 20/08/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

@Module
class UseCaseModule {
    @Provides
    fun provideGetSearchResultUseCase() = GetSearchResultUseCase()
}