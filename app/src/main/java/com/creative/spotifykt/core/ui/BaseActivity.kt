package com.creative.spotifykt.core.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.creative.spotifykt.core.toast
import com.creative.spotifykt.App
import com.creative.spotifykt.di.component.ActivityComponent
import com.creative.spotifykt.di.component.DaggerActivityComponent
import com.creative.spotifykt.di.module.ActivityModule
import com.creative.spotifykt.core.viewmodel.BaseViewModel
import javax.inject.Inject

abstract class BaseActivity<VB: ViewBinding, VM: BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    protected var viewBinding: VB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        viewBinding = provideViewBinding().apply {
            setContentView(root)
        }
        setupView(savedInstanceState)
        setupObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

    private fun buildActivityComponent() = DaggerActivityComponent.builder()
        .appComponent((application as App).appComponent)
        .activityModule(ActivityModule(this))
        .build()

    protected open fun setupObservers() {
        viewModel.messageString.observe(this) {
            it.run {
                showMess(this)
            }
        }
    }

    open fun showMess(msg: String) {
        toast(msg)
    }

    protected abstract fun provideViewBinding(): VB

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setupView(savedInstanceState: Bundle?)
}