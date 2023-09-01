package com.creative.spotifykt.data.model.local

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MusicListUI(val id: Int? = null,
    val headline: TextLabel? = null,
    val actionIcon: ActionIcon? = null,
    val items: List<MusicSquareUI?>? = null,
    val layout: LayoutConfig? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class ActionIcon(
    val image: String? = null,
    val deeplink: String? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class LayoutConfig(
    val orientation: String? = null,
    val spanCount: Int? = 0
) : Parcelable

enum class LayoutOrientation(val value: String) {
    VERTICAL("VERTICAL"), HORIZONTAL("HORIZONTAL")
}