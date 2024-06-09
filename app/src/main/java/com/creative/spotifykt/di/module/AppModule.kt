package com.creative.spotifykt.di.module

import android.content.Context
import com.creative.spotifykt.di.CacheDirectory
import com.creative.spotifykt.di.FileDirectory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @FileDirectory
    fun provideRootFilePath(@ApplicationContext context: Context): File {
        val fileRoot = File(context.filesDir.absolutePath + "/files/")
        if (!fileRoot.exists()) {
            fileRoot.mkdir()
        }
        return fileRoot
    }

    @Provides
    @Singleton
    @CacheDirectory
    fun provideRootCachePath(@ApplicationContext context: Context): File {
        val fileRootCache = File(context.cacheDir.absolutePath + "/caches/")
        if (!fileRootCache.exists()) {
            fileRootCache.mkdir()
        }
        return fileRootCache
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()
}