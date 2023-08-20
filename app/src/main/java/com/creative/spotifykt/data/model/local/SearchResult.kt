package com.creative.spotifykt.data.model.local

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class SearchResult(
    val id: String? = null,
    val type: String? = null,
    val searchResultText: SearchResultText? = null,
    val searchResultMusicRow: SearchResultMusicRow? = null,
    val searchResultFeatureList: SearchResultFeatureList? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class SearchResultText(
    val deeplink: String? = null,
    val textLabel: TextLabel? = null) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class SearchResultFeatureList(
    val deeplink: String? = null,
    val title: TextLabel? = null,
    val featureList: List<MusicSquareUI>? = null) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class SearchResultMusicRow(
    val deeplink: String? = null,
    val image: String? = null,
    val title: TextLabel? = null,
    val subTitle: TextLabel? = null) : Parcelable

enum class SearchResultType(val value: String) {
    TEXT("TEXT"),
    MUSIC_ROW("MUSIC_ROW"),
    FEATURE_LIST("FEATURE_LIST"),
}
