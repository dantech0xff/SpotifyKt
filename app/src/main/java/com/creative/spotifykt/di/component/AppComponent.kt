package com.creative.spotifykt.di.component

import android.content.Context
import com.creative.spotifykt.App
import com.creative.spotifykt.di.ApplicationContext
import com.creative.spotifykt.di.CacheDirectory
import com.creative.spotifykt.di.FileDirectory
import com.creative.spotifykt.di.getter.IUseCaseModule
import com.creative.spotifykt.di.module.AppModule
import com.creative.spotifykt.di.module.biz.DatabaseModule
import com.creative.spotifykt.di.module.biz.NetworkModule
import com.creative.spotifykt.di.module.biz.RepoModule
import com.creative.spotifykt.di.module.biz.UseCaseModule
import com.squareup.moshi.Moshi
import dagger.Component
import java.io.File
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    DatabaseModule::class,
    NetworkModule::class,
    RepoModule::class,
    UseCaseModule::class,
])
interface AppComponent : IUseCaseModule {
    fun inject(app: App)
    val application: App

    @ApplicationContext
    fun getContext(): Context

    @FileDirectory
    fun getFileRootPath(): File

    @CacheDirectory
    fun getCacheRootPath(): File

    val moshi: Moshi
}