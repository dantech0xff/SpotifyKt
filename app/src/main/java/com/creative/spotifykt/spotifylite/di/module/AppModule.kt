package com.creative.spotifykt.spotifylite.di.module

import android.content.Context
import com.creative.spotifykt.spotifylite.App
import com.creative.spotifykt.spotifylite.di.ApplicationContext
import com.creative.spotifykt.spotifylite.di.CacheDirectory
import com.creative.spotifykt.spotifylite.di.FileDirectory
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton

@Module
class AppModule (private val application: App) {

    @Provides
    @Singleton
    fun provideApplication(): App = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideAppContext(): Context = application

    @Provides
    @Singleton
    @FileDirectory
    fun provideRootFilePath(): File {
        val fileRoot = File(application.filesDir.absolutePath + "/files/")
        if (!fileRoot.exists()) {
            fileRoot.mkdir()
        }
        return fileRoot
    }

    @Provides
    @Singleton
    @CacheDirectory
    fun provideRootCachePath(): File {
        val fileRootCache = File(application.cacheDir.absolutePath + "/caches/")
        if (!fileRootCache.exists()) {
            fileRootCache.mkdir()
        }
        return fileRootCache
    }
}