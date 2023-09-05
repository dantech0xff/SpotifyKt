package com.creative.spotifykt.di.module

import androidx.lifecycle.ViewModelProvider
import com.creative.spotifykt.App
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.core.viewmodel.viewModelFactory
import com.creative.spotifykt.data.model.local.getFavoriteTab
import com.creative.spotifykt.ui.activity.setting.SettingActivityViewModel
import com.creative.spotifykt.ui.favorite.FavoriteViewModel
import com.creative.spotifykt.ui.favorite.list.ListFavoriteViewModel
import com.creative.spotifykt.ui.home.HomeViewModel
import com.creative.spotifykt.ui.premium.PremiumViewModel
import com.creative.spotifykt.ui.search.SearchViewModel
import com.creative.spotifykt.ui.search.result.SearchResultViewModel
import com.creative.spotifykt.ui.setting.about.AboutViewModel
import com.creative.spotifykt.ui.setting.account.AccountSettingViewModel
import com.creative.spotifykt.ui.setting.audio.AudioSettingViewModel
import com.creative.spotifykt.ui.setting.download.DownloadSettingViewModel
import com.creative.spotifykt.ui.setting.explicit.ExplicitContentViewModel
import com.creative.spotifykt.ui.setting.main.MainSettingViewModel
import com.creative.spotifykt.ui.setting.mobiledata.MobileDataViewModel
import com.creative.spotifykt.ui.setting.privacy.PrivacySettingViewModel
import com.creative.spotifykt.ui.setting.storage.StorageViewModel
import com.creative.spotifykt.usecase.favorite.GetFavoriteListUseCase
import com.creative.spotifykt.usecase.favorite.GetTabLayoutUseCase
import com.creative.spotifykt.usecase.home.GetHomeLayoutUseCase
import com.creative.spotifykt.usecase.search.GetSearchResultUseCase
import com.creative.spotifykt.usecase.search.GetSearchTopicUseCase
import com.creative.spotifykt.usecase.setting.GetMainSettingUseCase
import com.creative.spotifykt.usecase.setting.GetSettingMobileDataUseCase
import com.creative.spotifykt.usecase.setting.UpdateMobileDataLimitUseCase
import dagger.Module
import dagger.Provides

@Module
class FragmentModule (private val fragment: BaseFragment<*, *>) {

    @Provides
    fun provideHomeViewModel(app: App, getHomeLayoutUseCase: GetHomeLayoutUseCase): HomeViewModel =
        ViewModelProvider(fragment,
            viewModelFactory {
                HomeViewModel(app, getHomeLayoutUseCase)
            })[HomeViewModel::class.java]

    @Provides
    fun provideSettingFragmentViewModel(): SettingActivityViewModel =
        ViewModelProvider(fragment, viewModelFactory { SettingActivityViewModel() })[SettingActivityViewModel::class.java]

    @Provides
    fun provideMainSettingViewModel(getMainSettingUseCase: GetMainSettingUseCase): MainSettingViewModel =
        ViewModelProvider(fragment, viewModelFactory { MainSettingViewModel(getMainSettingUseCase) })[MainSettingViewModel::class.java]

    @Provides
    fun provideMobileDataViewModel(
        getSettingMobileDataUseCase: GetSettingMobileDataUseCase,
        updateMobileDataLimitUseCase: UpdateMobileDataLimitUseCase
    ): MobileDataViewModel {
        return ViewModelProvider(fragment, viewModelFactory {
            MobileDataViewModel(getSettingMobileDataUseCase, updateMobileDataLimitUseCase)
        })[MobileDataViewModel::class.java]
    }

    @Provides
    fun provideStorageViewModel(): StorageViewModel =
        ViewModelProvider(fragment, viewModelFactory { StorageViewModel() })[StorageViewModel::class.java]

    @Provides
    fun provideAudioSettingViewModel(): AudioSettingViewModel =
        ViewModelProvider(fragment, viewModelFactory { AudioSettingViewModel() })[AudioSettingViewModel::class.java]

    @Provides
    fun providePremiumViewModel(): PremiumViewModel =
        ViewModelProvider(fragment, viewModelFactory { PremiumViewModel() })[PremiumViewModel::class.java]

    @Provides
    fun provideDownloadSettingsViewModel(): DownloadSettingViewModel =
        ViewModelProvider(fragment, viewModelFactory { DownloadSettingViewModel() })[DownloadSettingViewModel::class.java]

    @Provides
    fun provideExplicitContentViewModel(): ExplicitContentViewModel =
        ViewModelProvider(fragment, viewModelFactory { ExplicitContentViewModel() })[ExplicitContentViewModel::class.java]

    @Provides
    fun provideAccountSettingViewModel(): AccountSettingViewModel =
        ViewModelProvider(fragment, viewModelFactory { AccountSettingViewModel() })[AccountSettingViewModel::class.java]

    @Provides
    fun provideAboutViewModel(): AboutViewModel =
        ViewModelProvider(fragment, viewModelFactory { AboutViewModel() })[AboutViewModel::class.java]

    @Provides
    fun provideSearchViewModel(getSearchTopicUseCase: GetSearchTopicUseCase): SearchViewModel =
        ViewModelProvider(fragment, viewModelFactory {
            SearchViewModel(getSearchTopicUseCase)
        })[SearchViewModel::class.java]

    @Provides
    fun provideFavoriteViewModel(
        getTabLayoutUseCase: GetTabLayoutUseCase
    ): FavoriteViewModel =
        ViewModelProvider(fragment, viewModelFactory {
            FavoriteViewModel(
                getTabLayoutUseCase
            )
        })[FavoriteViewModel::class.java]

    @Provides
    fun provideSearchResultViewModel(
        getSearchResultUseCase: GetSearchResultUseCase
    ): SearchResultViewModel =
        ViewModelProvider(fragment, viewModelFactory {
            SearchResultViewModel(
                getSearchResultUseCase
            )
        })[SearchResultViewModel::class.java]

    @Provides
    fun provideListFavoriteViewModel(
        getFavoriteListUseCase: GetFavoriteListUseCase
    ): ListFavoriteViewModel =
        ViewModelProvider(fragment, viewModelFactory {
            ListFavoriteViewModel(
                fragment.arguments?.getFavoriteTab(), getFavoriteListUseCase
            )
        })[ListFavoriteViewModel::class.java]

    @Provides
    fun providePrivacySettingViewModel(): PrivacySettingViewModel =
        ViewModelProvider(fragment, viewModelFactory { PrivacySettingViewModel() })[PrivacySettingViewModel::class.java]
}