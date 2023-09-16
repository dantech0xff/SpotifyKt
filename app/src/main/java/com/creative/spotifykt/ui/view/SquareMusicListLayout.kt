package com.creative.spotifykt.ui.view

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.R
import com.creative.spotifykt.data.model.local.LayoutOrientation
import com.creative.spotifykt.data.model.local.MusicListUI
import com.creative.spotifykt.databinding.LayoutSquareCellMusicListBinding
import com.creative.spotifykt.ui.IDeeplinkHandler
import com.creative.spotifykt.ui.home.MusicListAdapter

/**
 * Created by dan on 16/09/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

class SquareMusicListLayout(private val layoutInflater: LayoutInflater, private var handleDeeplink: IDeeplinkHandler? = null) {

    companion object {
        const val MAGIC_COL_PER_WIDTH_SCREEN_HORIZONTAL = 3
    }

    private val musicAdapter: MusicListAdapter by lazy {
        MusicListAdapter(handleDeeplink = handleDeeplink)
    }

    private val binding: LayoutSquareCellMusicListBinding by lazy {
        LayoutSquareCellMusicListBinding.inflate(layoutInflater).apply {
            recyclerView.adapter = musicAdapter
        }
    }

    val root: View by lazy {
        binding.root
    }

    fun bind(musicListUI: MusicListUI, viewLifecycle: LifecycleOwner) {

        val spanCount = musicListUI.layout?.spanCount ?: 1
        val orientation = musicListUI.layout?.orientation ?: LayoutOrientation.VERTICAL.value

        val screenWidth: Int = root.context.resources.displayMetrics.widthPixels
        val spaceLeftRight = root.context.resources.getDimensionPixelSize(R.dimen.xds_space_l)
        val spaceBetween = root.context.resources.getDimensionPixelSize(R.dimen.xds_space_s)

        binding.apply {
            data = musicListUI
            handleActionIconClick = handleDeeplink

            recyclerView.layoutManager = if (orientation == LayoutOrientation.HORIZONTAL.value) {
                recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        outRect.apply {
                            if (parent.getChildAdapterPosition(view) < spanCount) {
                                left = spaceLeftRight
                                right = spaceBetween / 2
                            } else {
                                right = spaceBetween / 2
                                left = spaceBetween / 2
                            }
                            top = spaceBetween / 2
                            bottom = spaceBetween / 2
                        }
                    }
                })
                object : GridLayoutManager(root.context, spanCount, HORIZONTAL, false) {
                    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                        lp?.apply {
                            width = (screenWidth - spaceLeftRight) / MAGIC_COL_PER_WIDTH_SCREEN_HORIZONTAL
                            height = ViewGroup.LayoutParams.WRAP_CONTENT
                        }
                        return super.checkLayoutParams(lp)
                    }
                }
            } else {
                recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        outRect.apply {
                            if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                                left = spaceLeftRight
                                right = spaceBetween / 2
                            } else {
                                right = spaceBetween / 2
                                left = spaceBetween / 2
                            }
                            top = spaceBetween / 2
                            bottom = spaceBetween / 2
                        }
                    }
                })
                object : GridLayoutManager(root.context, spanCount, VERTICAL, false) {
                    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                        lp?.apply {
                            width = (screenWidth - spaceLeftRight * 2 - spaceBetween * (spanCount - 1)) / spanCount
                            height = ViewGroup.LayoutParams.WRAP_CONTENT
                        }
                        return super.checkLayoutParams(lp)
                    }
                }
            }

            musicAdapter.submitList(musicListUI.items)
            lifecycleOwner = viewLifecycle

            executePendingBindings()
        }
    }
}