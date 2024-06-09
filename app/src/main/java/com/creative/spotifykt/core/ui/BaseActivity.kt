package com.creative.spotifykt.core.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity() {

    protected var viewBinding: VB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        viewBinding = provideViewBinding().apply {
            setContentView(root)
        }
        setupView(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

    protected abstract fun provideViewBinding(): VB

    protected abstract fun setupView(savedInstanceState: Bundle?)
}