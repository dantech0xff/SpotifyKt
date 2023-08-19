package com.creative.spotifykt.spotifylite.di.component

import com.creative.spotifykt.spotifylite.ui.main.MainActivity
import com.creative.spotifykt.spotifylite.di.ActivityScope
import com.creative.spotifykt.spotifylite.di.module.ActivityModule
import com.creative.spotifykt.spotifylite.ui.setting.SettingActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class],
    modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: SettingActivity)
}