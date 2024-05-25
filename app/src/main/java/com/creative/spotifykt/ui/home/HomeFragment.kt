package com.creative.spotifykt.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.creative.spotifykt.core.debugToast
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.data.model.local.MusicListUI
import com.creative.spotifykt.databinding.FragmentHomeBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.ui.IDeeplinkHandler
import com.creative.spotifykt.ui.view.SquareMusicListLayout
import com.creative.spotifykt.utils.handleDeeplinkInternal

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private val deeplinkHandler: IDeeplinkHandler by lazy {
        object : IDeeplinkHandler {
            @SuppressLint("QueryPermissionsNeeded")
            override fun handleDeeplink(deeplink: String?) {
                handleDeeplinkInternal(deeplink)
            }
        }
    }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.homeList.observe(viewLifecycleOwner) {
            when (it) {
                is HomeListState.Loading -> {
                    // show loading
                    Log.d("HomeFragment", "Loading")
                }

                is HomeListState.Error -> {
                    // show error
                    Log.d("HomeFragment", "Error")
                }

                is HomeListState.Success -> {
                    // show success
                    handleSuccess(it.data)
                }

                else -> {
                    // show empty
                    Log.d("HomeFragment", "Empty")
                }
            }
        }
    }

    private fun handleSuccess(list: List<MusicListUI>) {
        viewBinding?.let {
            it.homeContainer.removeAllViewsInLayout()
            list.forEach { musicListUI ->
                it.homeContainer.addView(
                    SquareMusicListLayout(layoutInflater, this@HomeFragment.deeplinkHandler).apply {
                        bind(musicListUI, viewLifecycleOwner)
                    }.root,
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                )
            }
        }
    }
}