package com.creative.spotifykt

import android.app.Application
import com.creative.spotifykt.di.component.AppComponent
import com.creative.spotifykt.di.component.DaggerAppComponent
import com.creative.spotifykt.di.module.AppModule

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