package com.creative.spotifykt.data.model.local

import com.creative.spotifykt.core.BaseModelData

data class FavMusicData(val id: String, val title: String, val subTitle: String, val thumbUrl: String = "") :
    BaseModelData {
    override fun toString(): String {
        return title + "\n" + subTitle
    }
}