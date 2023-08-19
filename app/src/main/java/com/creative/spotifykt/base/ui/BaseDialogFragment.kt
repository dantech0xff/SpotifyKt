package com.creative.spotifykt.base.ui

import com.creative.spotifykt.spotifylite.App
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import com.creative.spotifykt.core.toast
import com.creative.spotifykt.spotifylite.di.component.DaggerDialogFragmentComponent
import com.creative.spotifykt.base.viewmodel.BaseViewModel
import com.creative.spotifykt.spotifylite.di.component.DialogFragmentComponent
import com.creative.spotifykt.spotifylite.di.module.DialogFragmentModule
import javax.inject.Inject

abstract class BaseDialogFragment<VM: BaseViewModel> : DialogFragment() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildFragmentComponent())
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(provideLayoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(savedInstanceState)
        setupObservers()
    }


    protected open fun setupObservers() {
        viewModel.messageString.observe(this) {
            it.run {
                showMess(this)
            }
        }
    }

    open fun showMess(msg: String) {
        activity?.toast(msg)
    }

    private fun buildFragmentComponent(): DialogFragmentComponent =
        DaggerDialogFragmentComponent.builder().appComponent((requireContext().applicationContext as App).appComponent)
            .dialogFragmentModule(DialogFragmentModule(this))
            .build()

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies(buildFragmentComponent: DialogFragmentComponent)

    protected abstract fun setupView(savedInstanceState: Bundle?)
}