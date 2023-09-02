package com.creative.spotifykt.core

import android.content.Context
import android.widget.Toast
import com.creative.spotifykt.BuildConfig

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.debugToast(message: String) {
    if (BuildConfig.DEBUG) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}