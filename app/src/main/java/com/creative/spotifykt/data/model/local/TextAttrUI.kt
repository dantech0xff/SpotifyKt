package com.creative.spotifykt.data.model.local

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class TextUI(
    val texts: List<TextStyle?>? = null
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class TextAttr(
    val text: String? = null,
    val colorStyle: String? = null,
    val textStyle: String? = null
): Parcelable

enum class TextStyle(val value: String) {
    REGULAR("REGULAR"),
    BOLD("BOLD"),
}
