package com.creative.spotifykt.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.creative.spotifykt.core.toast

abstract class BaseFragment<VB: ViewBinding> : Fragment() {

    protected var viewBinding: VB? = null

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
    }

    open fun handleFragmentOnBackPressed() {}

    open fun showMess(msg: String) {
        activity?.toast(msg)
    }

    protected abstract fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    protected abstract fun setupView(savedInstanceState: Bundle?)

    protected open fun shouldInterceptBackPress(): Boolean = false
}