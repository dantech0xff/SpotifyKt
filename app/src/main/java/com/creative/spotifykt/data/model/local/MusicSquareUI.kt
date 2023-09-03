package com.creative.spotifykt.data.model.local

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MusicSquareUI(
    val id: String? = null,
    val image: String? = null,
    val deeplink: String? = null,
    val headline: TextLabel? = null,
    val subHeadline: TextLabel? = null,
) : Parcelable


@Parcelize
@JsonClass(generateAdapter = true)
data class TextLabel(
     val text: String? = null,
     val colorStyle: String? = null,
     val sizeStyle: String? = null,
) : Parcelable

enum class ColorStyle(val value: String) {
    PRIMARY("PRIMARY"),
}