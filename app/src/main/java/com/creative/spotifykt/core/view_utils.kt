package com.creative.spotifykt.core

import android.content.Context
import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView

// extension function to convert dp to equivalent pixels
// this method return integer value
fun Context.dpToPixelsInt(dp: Int):Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics
).toInt()

fun RecyclerView.removeAllDecorations() {
    while (itemDecorationCount > 0) {
        removeItemDecorationAt(0)
    }
}