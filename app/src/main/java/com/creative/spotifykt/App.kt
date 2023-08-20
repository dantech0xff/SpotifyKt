package com.creative.spotifykt

import android.app.Application
import android.os.StrictMode
import com.creative.spotifykt.di.component.AppComponent
import com.creative.spotifykt.di.component.DaggerAppComponent
import com.creative.spotifykt.di.module.AppModule
import com.creative.spotifykt.di.module.biz.UseCaseModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }
    }

    private fun injectDependencies() {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .databaseModule(com.creative.spotifykt.di.module.biz.DatabaseModule())
            .networkModule(com.creative.spotifykt.di.module.biz.NetworkModule())
            .repoModule(com.creative.spotifykt.di.module.biz.RepoModule())
            .useCaseModule(UseCaseModule())
            .build()
        appComponent.inject(this)
    }
}