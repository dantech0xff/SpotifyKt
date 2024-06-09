package com.creative.spotifykt.usecase.setting

import android.content.Context
import com.creative.spotifykt.App
import com.creative.spotifykt.R
import com.creative.spotifykt.data.model.local.SettingActionId.SETTING_DOWNLOAD
import com.creative.spotifykt.data.model.local.SettingRowType
import com.creative.spotifykt.data.model.local.SettingRowUI
import com.creative.spotifykt.data.model.local.TextLabel
import com.creative.spotifykt.ui.setting.main.MainSettingListState
import com.creative.spotifykt.usecase.BaseFlowUseCase
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by dan on 02/09/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

@ViewModelScoped
class GetMainSettingUseCase @Inject
     constructor(
    @ApplicationContext private val app: Context,
    private val moshi: Moshi
) : BaseFlowUseCase<Unit, MainSettingListState>() {
    override fun create(input: Unit): Flow<MainSettingListState> {
        return flow {
            emit(MainSettingListState.Empty)
            val listSetting = mutableListOf<SettingRowUI>().apply {
                add(
                    SettingRowUI(
                        rowType = SettingRowType.ROW_SETTING_TEXT.value,
                        deeplink = "spotifykt://app.setting/mobile-data",
                        title = TextLabel(text = app.getString(R.string.mobile_data), colorStyle = "PRIMARY"),
                        subTitle = TextLabel(
                            text = app.getString(R.string.byte_used_by_spotify_this_month).format("0.0 MB")
                        )
                    )
                )
                add(
                    SettingRowUI(
                        rowType = SettingRowType.ROW_SETTING_TEXT.value,
                        deeplink = "spotifykt://app.setting/storage",
                        title = TextLabel(text = app.getString(R.string.storage), colorStyle = "PRIMARY"),
                        subTitle = TextLabel(
                            text = app.getString(R.string.byte_used_by_spotify).format("66.0 MB")
                        )
                    )
                )
                add(
                    SettingRowUI(
                        rowType = SettingRowType.ROW_SETTING_TEXT.value,
                        deeplink = "",
                        title = TextLabel(text = app.getString(R.string.audio_settings), colorStyle = "PRIMARY"),
                        subTitle = TextLabel(
                            text = app.getString(R.string.audio_quality_s).format("Normal")
                        )
                    )
                )
                add(
                    SettingRowUI(
                        rowType = SettingRowType.ROW_SETTING_TEXT.value,
                        deeplink = "",
                        settingId = SETTING_DOWNLOAD,
                        title = TextLabel(text = app.getString(R.string.download_settings), colorStyle = "PRIMARY"),
                        subTitle = TextLabel(
                            text = app.getString(R.string.download_on_wi_fi_only)
                        )
                    )
                )
                add(
                    SettingRowUI(
                        rowType = SettingRowType.ROW_SETTING_TEXT.value,
                        deeplink = "",
                        title = TextLabel(
                            text = app.getString(R.string.privacy_settings), colorStyle = "PRIMARY"
                        ),
                        subTitle = TextLabel(
                            text = app.getString(R.string.manage_sharing_your_listening_activity_profile_and_more)
                        )
                    )
                )
                add(
                    SettingRowUI(
                        rowType = SettingRowType.ROW_SETTING_TEXT.value,
                        deeplink = "",
                        title = TextLabel(
                            text = app.getString(R.string.explicit_content), colorStyle = "PRIMARY"
                        ),
                        subTitle = TextLabel(
                            text = app.getString(R.string.explicit_content_allowed)
                        )
                    )
                )
                add(
                    SettingRowUI(
                        rowType = SettingRowType.ROW_SETTING_TEXT.value,
                        deeplink = "",
                        title = TextLabel(
                            text = app.getString(R.string.account), colorStyle = "PRIMARY"
                        ),
                        subTitle = TextLabel(
                            text = app.getString(R.string.free_account)
                        )
                    )
                )
                add(
                    SettingRowUI(
                        rowType = SettingRowType.ROW_SETTING_TEXT.value,
                        deeplink = "",
                        title = TextLabel(
                            text = app.getString(R.string.about), colorStyle = "PRIMARY"
                        ),
                        subTitle = TextLabel(
                            text = app.getString(R.string.spotify_lite_version_s).format("1.9.043809")
                        )
                    )
                )
                add(SettingRowUI(rowType = SettingRowType.ROW_SECTION_SEPARATE.value))
                add(
                    SettingRowUI(
                        rowType = SettingRowType.ROW_SETTING_TEXT.value,
                        deeplink = "",
                        title = TextLabel(text = app.getString(R.string.try_spotify_music), colorStyle = "PRIMARY"),
                        iconUrl = "https://1000logos.net/wp-content/uploads/2016/10/Android-Logo.png"
                    )
                )
                add(
                    SettingRowUI(
                        rowType = SettingRowType.ROW_SETTING_TEXT.value,
                        deeplink = "",
                        title = TextLabel(text = app.getString(R.string.logout), colorStyle = "PRIMARY"),
                        iconUrl = "https://1000logos.net/wp-content/uploads/2016/10/Android-Logo.png"
                    )
                )
            }
            emit(MainSettingListState.Success(listSetting))
        }.flowOn(Dispatchers.IO)
    }
}