package com.creative.spotifykt.di.component

import android.content.Context
import com.creative.spotifykt.App
import com.creative.spotifykt.di.ApplicationContext
import com.creative.spotifykt.di.CacheDirectory
import com.creative.spotifykt.di.FileDirectory
import com.creative.spotifykt.di.module.AppModule
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