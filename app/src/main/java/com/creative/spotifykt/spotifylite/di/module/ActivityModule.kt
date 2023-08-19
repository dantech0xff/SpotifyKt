package com.creative.spotifykt.spotifylite.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.creative.spotifykt.base.viewmodel.viewModelFactory
import com.creative.spotifykt.base.ui.BaseActivity
import com.creative.spotifykt.spotifylite.ui.main.MainActivityViewModel
import com.creative.spotifykt.spotifylite.ui.setting.SettingActivityViewModel
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