package com.creative.spotifykt.core

import android.util.Log

fun log(message: String) {
    Log.d("SpotifyKt", message)
}

fun log(tag: String, message: String) {
    Log.d(tag, message)
}

fun log(throwable: Throwable) {
    Log.e("SpotifyKt", throwable.message ?: "Unknown error")
}