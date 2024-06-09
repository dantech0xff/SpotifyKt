package com.creative.spotifykt.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class CacheDirectory

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class FileDirectory