package com.creative.spotifykt.di.module.biz

import com.creative.spotifykt.App
import com.creative.spotifykt.usecase.favorite.GetFavoriteListUseCase
import com.creative.spotifykt.usecase.favorite.GetTabLayoutUseCase
import com.creative.spotifykt.usecase.search.GetSearchResultUseCase
import com.creative.spotifykt.usecase.search.GetSearchTopicUseCase
import com.squareup.moshi.Moshi
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
    fun provideGetSearchResultUseCase(
        app: App, moshi: Moshi
    ) = GetSearchResultUseCase(app, moshi)

    @Provides
    fun provideGetSearchTopicUseCase(
        app: App, moshi: Moshi
    ) = GetSearchTopicUseCase(app, moshi)

    @Provides
    fun provideGetTabLayoutUseCase(
        app: App, moshi: Moshi
    ) = GetTabLayoutUseCase(app, moshi)

    @Provides
    fun provideGetFavoriteListUseCase(
        app: App, moshi: Moshi
    ) = GetFavoriteListUseCase(app, moshi)
}