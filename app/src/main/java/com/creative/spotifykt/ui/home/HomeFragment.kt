package com.creative.spotifykt.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.creative.spotifykt.core.log
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.data.model.local.MusicListUI
import com.creative.spotifykt.databinding.FragmentHomeBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.ui.IDeeplinkHandler
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
        val start = System.currentTimeMillis()
        viewBinding?.let {
            val childCount = it.homeContainer.childCount
            val listCount = list.size
            for (i in 0 until childCount) {
                it.homeContainer.getChildAt(i).visibility = View.INVISIBLE
            }
            if (listCount > childCount) {
                val diff = listCount - childCount
                for (i in 0 until diff) {
                    val view = SquareMusicListLayout(requireContext())
                    view.visibility = View.INVISIBLE
                    it.homeContainer.addView(
                        view,
                        LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                    )
                }
                log("HomeFragment", "listCount: $listCount, childCount: $childCount")
            }
            list.forEachIndexed { index, musicListUI ->
                val view = it.homeContainer.getChildAt(index)
                if (view is SquareMusicListLayout) {
                    view.visibility = View.VISIBLE
                    view.bind(musicListUI)
                }
            }
        }
        log("HomeFragment", "handleSuccess: ${System.currentTimeMillis() - start}")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("HomeFragment", "onDestroy $this")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        log("HomeFragment", "onDestroyView $this")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("HomeFragment", "onCreate $this")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        log("HomeFragment", "onCreateView $this")
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}