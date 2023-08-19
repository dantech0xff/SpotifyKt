package com.creative.spotifykt.spotifylite

import android.app.Application
import com.creative.spotifykt.spotifylite.di.component.AppComponent
import com.creative.spotifykt.spotifylite.di.component.DaggerAppComponent
import com.creative.spotifykt.spotifylite.di.module.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
        appComponent.inject(this)
    }
}