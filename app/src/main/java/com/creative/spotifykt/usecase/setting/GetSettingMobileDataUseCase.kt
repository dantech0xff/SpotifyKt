package com.creative.spotifykt.usecase.setting

import com.creative.spotifykt.App
import com.creative.spotifykt.ui.setting.mobiledata.SettingMobileDataState
import com.creative.spotifykt.usecase.BaseFlowUseCase
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

data class GetSettingMobileDataUseCase(
    private val app: App, private val moshi: Moshi
) : BaseFlowUseCase<Unit, SettingMobileDataState>() {
    override fun create(input: Unit): Flow<SettingMobileDataState> {
        return flow {
            emit(SettingMobileDataState.Empty)
        }.flowOn(Dispatchers.IO)
    }
}
