package com.creative.spotifykt.spotifylite.data

import com.creative.spotifykt.base.data.BaseModelData

data class FavMusicData(val id: String, val title: String, val subTitle: String, val thumbUrl: String = "") :
    BaseModelData {
    override fun toString(): String {
        return title + "\n" + subTitle
    }
}