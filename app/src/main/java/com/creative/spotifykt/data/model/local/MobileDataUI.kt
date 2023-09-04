package com.creative.spotifykt.data.model.local

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class MobileDataUI(
    val currentAmount: TextLabel? = null,
    val currentUnit: TextLabel? = null,
    val limitAmount: TextLabel? = null,
    val limitUnit: TextLabel? = null,
    val headline: TextLabel? = null,
    val subHeadline: TextLabel? = null,
    val dataProgressBarUI: DataProgressBarUI? = null,
    val headlineSetDailyLimit: TextLabel? = null,
    val subHeadlineSetDailyLimit: TextLabel? = null,
    val listLimit: List<DataLimitCellUI>? = null,
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class DataLimitCellUI(
    val inactiveText: TextLabel? = null,
    val activeText: TextLabel? = null
): Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class DataProgressBarUI(
    val minText: TextLabel? = null,
    val maxText: TextLabel? = null,
    val progressValue: Float? = null,
    val usedText: TextLabel? = null,
    val remainingText: TextLabel? = null
): Parcelable
