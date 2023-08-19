package com.creative.spotifykt.spotifylite.data

import com.creative.spotifykt.base.data.BaseModelData

data class SearchResultData(val type: SearchResultType,
                            val imgUrl: String = "",
                            val title: String,
                            val subTitle: String = "",
                            val featureList: List<MusicSquareUI>? = null,
                            val metaData: String = "") : BaseModelData

enum class SearchResultType {
    MUSIC, FEATURE, ADDITIONAL
}
