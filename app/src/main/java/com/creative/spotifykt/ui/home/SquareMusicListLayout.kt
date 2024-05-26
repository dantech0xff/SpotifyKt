package com.creative.spotifykt.ui.home

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.R
import com.creative.spotifykt.data.model.local.LayoutOrientation
import com.creative.spotifykt.data.model.local.MusicListUI
import com.creative.spotifykt.databinding.LayoutSquareCellMusicListBinding
import com.creative.spotifykt.ui.IDeeplinkHandler

/**
 * Created by dan on 26/5/24
 *
 * Copyright © 2024 1010 Creative. All rights reserved.
 */

class SquareMusicListLayout : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var binding: LayoutSquareCellMusicListBinding = LayoutSquareCellMusicListBinding.inflate(LayoutInflater.from(context), this, true)

    companion object {
        const val MAGIC_COL_PER_WIDTH_SCREEN_HORIZONTAL = 3
    }

    private val musicAdapter: MusicListAdapter by lazy {
        MusicListAdapter()
    }

    fun bind(musicListUI: MusicListUI, deeplinkHandler: IDeeplinkHandler? = null) {
        // do something
        val spanCount = musicListUI.layout?.spanCount ?: 1
        val orientation = musicListUI.layout?.orientation ?: LayoutOrientation.VERTICAL.value

        val screenWidth: Int = context.resources.displayMetrics.widthPixels
        val spaceLeftRight = context.resources.getDimensionPixelSize(R.dimen.xds_space_l)
        val spaceBetween = context.resources.getDimensionPixelSize(R.dimen.xds_space_s)
        binding.apply {
            data = musicListUI
            handleActionIconClick = deeplinkHandler
            recyclerView.adapter = musicAdapter.apply {
                this.handleDeeplink = deeplinkHandler
            }
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
            executePendingBindings()
        }
    }
}