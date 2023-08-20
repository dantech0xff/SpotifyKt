package com.creative.spotifykt.data.model.local

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class ListFavoriteMusicRow(
    val items: List<FavoriteMusicRow?>? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class FavoriteMusicRow(
    val deeplink: String? = null,
    val image: String? = null,
    val headline: TextLabel? = null,
    val subHeadline: TextLabel? = null
) : Parcelable

fun FavoriteMusicRow.toSearchResultMusicRow(): SearchResultMusicRow {
    return SearchResultMusicRow(
        deeplink = deeplink,
        image = image,
        headline = headline,
        subHeadline = subHeadline
    )
}