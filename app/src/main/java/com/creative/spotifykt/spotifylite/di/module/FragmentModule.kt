package com.creative.spotifykt.spotifylite.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.creative.spotifykt.base.viewmodel.viewModelFactory
import com.creative.spotifykt.base.ui.BaseFragment
import com.creative.spotifykt.spotifylite.App
import com.creative.spotifykt.spotifylite.ui.favorite.FavoriteViewModel
import com.creative.spotifykt.spotifylite.ui.favorite.list.ListFavoriteViewModel
import com.creative.spotifykt.spotifylite.ui.setting.download.DownloadSettingViewModel
import com.creative.spotifykt.spotifylite.ui.home.HomeFragmentViewModel
import com.creative.spotifykt.spotifylite.ui.premium.PremiumViewModel
import com.creative.spotifykt.spotifylite.ui.search.SearchViewModel
import com.creative.spotifykt.spotifylite.ui.search.result.SearchResultViewModel
import com.creative.spotifykt.spotifylite.ui.setting.SettingActivityViewModel
import com.creative.spotifykt.spotifylite.ui.setting.about.AboutViewModel
import com.creative.spotifykt.spotifylite.ui.setting.account.AccountSettingViewModel
import com.creative.spotifykt.spotifylite.ui.setting.audio.AudioSettingViewModel
import com.creative.spotifykt.spotifylite.ui.setting.explicit.ExplicitContentViewModel
import com.creative.spotifykt.spotifylite.ui.setting.main.MainSettingViewModel
import com.creative.spotifykt.spotifylite.ui.setting.mobiledata.MobileDataViewModel
import com.creative.spotifykt.spotifylite.ui.setting.storage.StorageViewModel
import dagger.Module
import dagger.Provides

@Module
class FragmentModule (private val fragment: BaseFragment<*, *>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideHomeViewModel(app: App): HomeFragmentViewModel =
        ViewModelProvider(fragment.requireActivity(),
            viewModelFactory {
                HomeFragmentViewModel(app)
            })[HomeFragmentViewModel::class.java]

    @Provides
    fun provideSettingFragmentViewModel(): SettingActivityViewModel =
        ViewModelProvider(fragment.requireActivity(), viewModelFactory { SettingActivityViewModel() })[SettingActivityViewModel::class.java]

    @Provides
    fun provideMainSettingViewModel(): MainSettingViewModel =
        ViewModelProvider(fragment.requireActivity(), viewModelFactory { MainSettingViewModel() })[MainSettingViewModel::class.java]

    @Provides
    fun provideMobileDataViewModel(): MobileDataViewModel =
        ViewModelProvider(fragment.requireActivity(), viewModelFactory { MobileDataViewModel() })[MobileDataViewModel::class.java]

    @Provides
    fun provideStorageViewModel(): StorageViewModel =
        ViewModelProvider(fragment.requireActivity(), viewModelFactory { StorageViewModel() })[StorageViewModel::class.java]

    @Provides
    fun provideAudioSettingViewModel(): AudioSettingViewModel =
        ViewModelProvider(fragment.requireActivity(), viewModelFactory { AudioSettingViewModel() })[AudioSettingViewModel::class.java]

    @Provides
    fun providePremiumViewModel(): PremiumViewModel =
        ViewModelProvider(fragment.requireActivity(), viewModelFactory { PremiumViewModel() })[PremiumViewModel::class.java]

    @Provides
    fun provideDownloadSettingsViewModel(): DownloadSettingViewModel =
        ViewModelProvider(fragment.requireActivity(), viewModelFactory { DownloadSettingViewModel() })[DownloadSettingViewModel::class.java]

    @Provides
    fun provideExplicitContentViewModel(): ExplicitContentViewModel =
        ViewModelProvider(fragment.requireActivity(), viewModelFactory { ExplicitContentViewModel() })[ExplicitContentViewModel::class.java]

    @Provides
    fun provideAccountSettingViewModel(): AccountSettingViewModel =
        ViewModelProvider(fragment.requireActivity(), viewModelFactory { AccountSettingViewModel() })[AccountSettingViewModel::class.java]

    @Provides
    fun provideAboutViewModel(): AboutViewModel =
        ViewModelProvider(fragment.requireActivity(), viewModelFactory { AboutViewModel() })[AboutViewModel::class.java]

    @Provides
    fun provideSearchViewModel(): SearchViewModel =
        ViewModelProvider(fragment.requireActivity(), viewModelFactory { SearchViewModel() })[SearchViewModel::class.java]

    @Provides
    fun provideFavoriteViewModel(): FavoriteViewModel =
        ViewModelProvider(fragment.requireActivity(), viewModelFactory { FavoriteViewModel() })[FavoriteViewModel::class.java]

    @Provides
    fun provideSearchResultViewModel(): SearchResultViewModel =
        ViewModelProvider(fragment.requireActivity(), viewModelFactory { SearchResultViewModel() })[SearchResultViewModel::class.java]

    @Provides
    fun provideListFavoriteViewModel(): ListFavoriteViewModel =
        ViewModelProvider(fragment.requireActivity(), viewModelFactory { ListFavoriteViewModel() })[ListFavoriteViewModel::class.java]
}