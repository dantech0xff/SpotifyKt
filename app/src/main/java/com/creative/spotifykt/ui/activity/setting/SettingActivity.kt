package com.creative.spotifykt.ui.activity.setting

import android.os.Bundle
import com.creative.spotifykt.core.ui.BaseActivity
import com.creative.spotifykt.databinding.ActivitySettingsBinding
import com.creative.spotifykt.di.component.ActivityComponent

class SettingActivity : BaseActivity<ActivitySettingsBinding, SettingActivityViewModel>() {
    override fun provideViewBinding(): ActivitySettingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {}
}