package com.creative.spotifykt.data.model.local

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class FavMusicTab(
    val id: String? = null,
    val title: String? = null,
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class ListFavMusicTab(
    val items: List<FavMusicTab?>? = null,
): Parcelable

enum class FavMusicTabType(val value: String) {
    PLAYLIST("PLAYLIST"),
    ARTIST("ARTIST"),
    ALBUM("ALBUM"),
    PODCAST("PODCAST")
}

fun Bundle.getFavoriteTab(): FavMusicTab? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(FavMusicTab::class.java.name, FavMusicTab::class.java)
    } else {
        getParcelable(FavMusicTab::class.java.name)
    }
}