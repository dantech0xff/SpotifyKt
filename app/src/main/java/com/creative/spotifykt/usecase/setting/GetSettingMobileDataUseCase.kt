package com.creative.spotifykt.usecase.setting

import com.creative.spotifykt.App
import com.creative.spotifykt.data.model.local.MobileDataUI
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
            val jsonAdapter = moshi.adapter(MobileDataUI::class.java)
            val mobileDataUI: MobileDataUI? = kotlin.runCatching {
                jsonAdapter.fromJson(runCatching {
                    app.assets.open("setting-mobile-data.json").let {
                        val size = it.available()
                        val buffer = ByteArray(size)
                        it.read(buffer)
                        it.close()
                        String(buffer, Charsets.UTF_8)
                    }
                }.onFailure {
                    com.creative.spotifykt.core.log(it.message ?: "Error")
                }.getOrDefault(""))
            }.onFailure {
                com.creative.spotifykt.core.log(it.message ?: "Error")
            }.getOrDefault(null)
            if (mobileDataUI == null)
                emit(SettingMobileDataState.Error)
            else {
                emit(SettingMobileDataState.Success(mobileDataUI))
            }
        }.flowOn(Dispatchers.IO)
    }
}
