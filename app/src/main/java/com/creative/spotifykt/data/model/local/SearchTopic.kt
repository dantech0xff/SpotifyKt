package com.creative.spotifykt.data.model.local

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class SearchTopic(
    val title: String? = null,
    val color: String? = "#FF424242",
    val deeplink: String? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class ListSearchTopic(
    val items: List<SearchTopic?>? = null
) : Parcelable