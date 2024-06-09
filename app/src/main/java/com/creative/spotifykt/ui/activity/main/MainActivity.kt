package com.creative.spotifykt.ui.activity.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.creative.spotifykt.R
import com.creative.spotifykt.core.ui.BaseActivity
import com.creative.spotifykt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun provideViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupView(savedInstanceState: Bundle?) {
        window?.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        viewBinding?.bottomNavView?.apply {
            menu.add(
                0, R.id.home_navigation, 0, getString(R.string.title_home)
            ).apply {
                icon = AppCompatResources.getDrawable(context, R.drawable.ic_home_black_24dp)
            }
            menu.add(
                0, R.id.search_navigation, 0, getString(R.string.title_search)
            ).apply {
                icon = AppCompatResources.getDrawable(context, R.drawable.round_find_in_page_24)
            }
            menu.add(
                0, R.id.favorite_navigation, 0, getString(R.string.title_favorite)
            ).apply {
                icon = AppCompatResources.getDrawable(context, R.drawable.outline_favorite_24)
            }
            menu.add(
                0, R.id.premium_navigation, 0, getString(R.string.title_premium)
            ).apply {
                icon = AppCompatResources.getDrawable(context, R.drawable.graphic_eq)
            }
            NavigationUI.setupWithNavController(this, findNavController(R.id.nav_host_fragment))
        }

        viewBinding?.apply {
            ViewCompat.setOnApplyWindowInsetsListener(root) { _, insets ->
                root.setPadding(
                    0, insets.getInsets(WindowInsetsCompat.Type.systemBars()).top, 0, 0
                )
                insets
            }
        }
    }
}