package com.creative.spotifykt.di.component

import com.creative.spotifykt.ui.activity.main.MainActivity
import com.creative.spotifykt.di.ActivityScope
import com.creative.spotifykt.di.module.ActivityModule
import com.creative.spotifykt.ui.activity.setting.SettingActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class],
    modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: SettingActivity)
}