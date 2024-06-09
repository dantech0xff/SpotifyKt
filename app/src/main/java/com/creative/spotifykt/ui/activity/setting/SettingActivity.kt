package com.creative.spotifykt.ui.activity.setting

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.creative.spotifykt.core.ui.BaseActivity
import com.creative.spotifykt.databinding.ActivitySettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : BaseActivity<ActivitySettingsBinding>() {
    override fun provideViewBinding(): ActivitySettingsBinding = ActivitySettingsBinding.inflate(layoutInflater)

    override fun setupView(savedInstanceState: Bundle?) {
        window?.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
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