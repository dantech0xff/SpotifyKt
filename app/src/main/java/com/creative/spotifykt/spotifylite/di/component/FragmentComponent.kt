package com.creative.spotifykt.spotifylite.di.component

import com.creative.spotifykt.spotifylite.di.FragmentScope
import com.creative.spotifykt.spotifylite.di.module.FragmentModule
import com.creative.spotifykt.spotifylite.ui.favorite.FavoriteFragment
import com.creative.spotifykt.spotifylite.ui.favorite.list.ListFavoriteFragment
import com.creative.spotifykt.spotifylite.ui.home.HomeFragment
import com.creative.spotifykt.spotifylite.ui.premium.PremiumFragment
import com.creative.spotifykt.spotifylite.ui.search.SearchFragment
import com.creative.spotifykt.spotifylite.ui.search.result.SearchResultFragment
import com.creative.spotifykt.spotifylite.ui.setting.SettingActivity
import com.creative.spotifykt.spotifylite.ui.setting.about.AboutFragment
import com.creative.spotifykt.spotifylite.ui.setting.account.AccountSettingFragment
import com.creative.spotifykt.spotifylite.ui.setting.audio.AudioSettingFragment
import com.creative.spotifykt.spotifylite.ui.setting.download.DownloadSettingFragment
import com.creative.spotifykt.spotifylite.ui.setting.explicit.ExplicitContentFragment
import com.creative.spotifykt.spotifylite.ui.setting.main.MainSettingFragment
import com.creative.spotifykt.spotifylite.ui.setting.mobiledata.MobileDataFragment
import com.creative.spotifykt.spotifylite.ui.setting.storage.StorageFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class],
    modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(fragment: HomeFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: PremiumFragment)
    fun inject(fragment: SettingActivity)
    fun inject(fragment: MainSettingFragment)
    fun inject(fragment: MobileDataFragment)
    fun inject(fragment: StorageFragment)
    fun inject(fragment: AudioSettingFragment)
    fun inject(fragment: DownloadSettingFragment)
    fun inject(fragment: ExplicitContentFragment)
    fun inject(fragment: AccountSettingFragment)
    fun inject(fragment: AboutFragment)
    fun inject(fragment: SearchResultFragment)
    fun inject(fragment: ListFavoriteFragment)
}