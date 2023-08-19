package com.creative.spotifykt.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.creative.spotifykt.R
import com.creative.spotifykt.core.ui.BaseActivity
import com.creative.spotifykt.databinding.ActivityMainBinding
import com.creative.spotifykt.di.component.ActivityComponent

class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    override fun provideViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.bottomNavView?.setupWithNavController(findNavController(R.id.nav_host_fragment))
    }
}