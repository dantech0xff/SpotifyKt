package com.creative.spotifykt.spotifylite.di.component

import android.content.Context
import com.creative.spotifykt.spotifylite.App
import com.creative.spotifykt.spotifylite.di.ApplicationContext
import com.creative.spotifykt.spotifylite.di.CacheDirectory
import com.creative.spotifykt.spotifylite.di.FileDirectory
import com.creative.spotifykt.spotifylite.di.module.AppModule
import dagger.Component
import java.io.File
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: App)
    fun getApplication(): App

    @ApplicationContext
    fun getContext(): Context

    @FileDirectory
    fun getFileRootPath(): File

    @CacheDirectory
    fun getCacheRootPath(): File
}