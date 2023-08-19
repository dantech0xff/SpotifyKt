package com.creative.spotifykt.di.component

import com.creative.spotifykt.di.FragmentScope
import com.creative.spotifykt.di.module.FragmentModule
import com.creative.spotifykt.ui.favorite.FavoriteFragment
import com.creative.spotifykt.ui.favorite.list.ListFavoriteFragment
import com.creative.spotifykt.ui.home.HomeFragment
import com.creative.spotifykt.ui.premium.PremiumFragment
import com.creative.spotifykt.ui.search.SearchFragment
import com.creative.spotifykt.ui.search.result.SearchResultFragment
import com.creative.spotifykt.ui.setting.SettingActivity
import com.creative.spotifykt.ui.setting.about.AboutFragment
import com.creative.spotifykt.ui.setting.account.AccountSettingFragment
import com.creative.spotifykt.ui.setting.audio.AudioSettingFragment
import com.creative.spotifykt.ui.setting.download.DownloadSettingFragment
import com.creative.spotifykt.ui.setting.explicit.ExplicitContentFragment
import com.creative.spotifykt.ui.setting.main.MainSettingFragment
import com.creative.spotifykt.ui.setting.mobiledata.MobileDataFragment
import com.creative.spotifykt.ui.setting.storage.StorageFragment
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