package com.creative.spotifykt.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.creative.spotifykt.core.toast
import com.creative.spotifykt.core.viewmodel.BaseViewModel
import com.creative.spotifykt.App
import com.creative.spotifykt.di.component.DaggerFragmentComponent
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.di.module.FragmentModule
import javax.inject.Inject

abstract class BaseFragment<VB: ViewBinding, VM: BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModel: VM

    protected var viewBinding: VB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildFragmentComponent())
        super.onCreate(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = provideViewBinding(inflater, container)
        return viewBinding!!.root
    }

    fun requireViewBinding() : VB {
        return viewBinding!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (shouldInterceptBackPress()) {
                    // in here you can do logic when backPress is clicked
                    handleFragmentOnBackPressed()
                } else {
                    isEnabled = false
                    activity?.onBackPressed()
                }
            }
        })
        setupView(savedInstanceState)
        setupObservers()
    }

    open fun handleFragmentOnBackPressed() {}

    private fun buildFragmentComponent(): FragmentComponent =
        DaggerFragmentComponent.builder()
            .appComponent((requireContext().applicationContext as App).appComponent)
            .fragmentModule(FragmentModule(this))
            .build()

    protected open fun setupObservers() {
        viewModel.messageString.observe(viewLifecycleOwner) {
            it.run {
                showMess(this)
            }
        }
    }

    open fun showMess(msg: String) {
        activity?.toast(msg)
    }

    protected abstract fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    protected abstract fun injectDependencies(fragmentComponent: FragmentComponent)

    protected abstract fun setupView(savedInstanceState: Bundle?)

    protected open fun shouldInterceptBackPress(): Boolean = false
}