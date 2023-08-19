package com.creative.spotifykt.di.module

import androidx.lifecycle.ViewModelProvider
import com.creative.spotifykt.core.viewmodel.viewModelFactory
import com.creative.spotifykt.core.ui.BaseActivity
import com.creative.spotifykt.ui.main.MainActivityViewModel
import com.creative.spotifykt.ui.setting.SettingActivityViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: BaseActivity<*, *>) {
    @Provides
    fun provideMainActivityViewModel(): MainActivityViewModel =
        ViewModelProvider(activity, viewModelFactory { MainActivityViewModel() })[MainActivityViewModel::class.java]

    @Provides
    fun provideSettingActivityViewModel(): SettingActivityViewModel =
        ViewModelProvider(activity, viewModelFactory { SettingActivityViewModel() })[SettingActivityViewModel::class.java]
}