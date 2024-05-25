package com.creative.spotifykt.utils

import android.content.Intent
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.creative.spotifykt.R
import com.creative.spotifykt.core.debugToast

/**
 * Created by dan on 25/5/24
 *
 * Copyright © 2024 1010 Creative. All rights reserved.
 */
 
fun Fragment.handleDeeplinkInternal(deeplink: String?) {
    // Handle deeplink here
    if (!deeplink.isNullOrEmpty()) {
        activity?.apply {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = android.net.Uri.parse(deeplink)
            }
            if (packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL).isEmpty()) {
                debugToast("No app found to handle the deeplink")
                return
            }
            startActivity(
                Intent(Intent.ACTION_VIEW).apply {
                    data = android.net.Uri.parse(deeplink)
                }
            )
            activity?.debugToast(deeplink)
        }
    }
}

fun NavController.handleDeeplinkInternal(deeplink: String?): Boolean {
    return graph.hasDeepLink(android.net.Uri.parse(deeplink)).let { hasDeepLink ->
        if (hasDeepLink) {
            navigate(
                android.net.Uri.parse(deeplink), NavOptions.Builder()
                    .setEnterAnim(R.anim.fade_in_anim)
                    .setExitAnim(R.anim.fade_out_anim)
                    .setPopEnterAnim(R.anim.fade_in_anim)
                    .setPopExitAnim(R.anim.fade_out_anim)
                    .build()
            )
        }
        hasDeepLink
    }
}