package com.creative.spotifykt.ui.home

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.creative.spotifykt.R
import com.creative.spotifykt.core.removeAllDecorations
import com.creative.spotifykt.databinding.LayoutSquareCellMusicListBinding
import com.creative.spotifykt.data.model.local.LayoutOrientation
import com.creative.spotifykt.data.model.local.MusicListUI
import com.creative.spotifykt.ui.IDeeplinkHandler

class HomeListAdapter(
    private val handleDeeplink: IDeeplinkHandler? = null
) : ListAdapter<MusicListUI, HomeListAdapter.HomeMusicVH>(DIFF_CALLBACK) {

    private val squareRecyclerPool = RecyclerView.RecycledViewPool()

    inner class HomeMusicVH(private val itemViewBinding: LayoutSquareCellMusicListBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        private val musicAdapter: MusicListAdapter by lazy {
            MusicListAdapter(handleDeeplink = handleDeeplink)
        }

        init {
            itemViewBinding.apply {
                recyclerView.adapter = musicAdapter
                recyclerView.setRecycledViewPool(squareRecyclerPool)
            }
        }

        fun bind(item: MusicListUI) {
            itemViewBinding.apply {
                recyclerView.removeAllDecorations()

                data = item
                handleActionIconClick = handleDeeplink

                val spanCount = item.layoutConfig?.spanCount ?: 1
                val orientation = item.layoutConfig?.orientation ?: LayoutOrientation.VERTICAL.value

                val screenWidth: Int = root.context.resources.displayMetrics.widthPixels
                val spaceLeftRight = root.context.resources.getDimensionPixelSize(R.dimen.xds_space_l)
                val spaceBetween = root.context.resources.getDimensionPixelSize(R.dimen.xds_space_s)

                recyclerView.layoutManager = if (orientation == LayoutOrientation.HORIZONTAL.value) {
                    recyclerView.addItemDecoration(object : ItemDecoration() {
                        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                            super.getItemOffsets(outRect, view, parent, state)
                            (view as? ViewGroup)?.apply {
                                layoutParams = layoutParams.apply {
                                    width = (screenWidth - spaceLeftRight) / MAGIC_COL_PER_WIDTH_SCREEN_HORIZONTAL
                                    height = LayoutParams.WRAP_CONTENT
                                }
                            }
                            val index = parent.getChildAdapterPosition(view)
                            if (index < spanCount) {
                                outRect.left = spaceLeftRight
                                outRect.right = spaceBetween / 2
                            } else {
                                outRect.right = spaceBetween / 2
                                outRect.left = spaceBetween / 2
                            }
                            outRect.top = spaceBetween / 2
                            outRect.bottom = spaceBetween / 2
                        }
                    })
                    if (spanCount > 1) {
                        GridLayoutManager(root.context, spanCount, GridLayoutManager.HORIZONTAL, false).apply {
                            recycleChildrenOnDetach = true
                        }
                    } else {
                        LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false).apply {
                            recycleChildrenOnDetach = true
                        }
                    }
                } else {
                    recyclerView.addItemDecoration(object : ItemDecoration() {
                        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                            super.getItemOffsets(outRect, view, parent, state)
                            (view as? ViewGroup)?.apply {
                                layoutParams = layoutParams.apply {
                                    width = (screenWidth - spaceLeftRight * 2 - spaceBetween * (spanCount - 1)) / spanCount
                                    height = LayoutParams.WRAP_CONTENT
                                }
                            }

                            val index = parent.getChildAdapterPosition(view)
                            if (index % spanCount == 0) {
                                outRect.left = spaceLeftRight
                                outRect.right = spaceBetween / 2
                            } else {
                                outRect.right = spaceBetween / 2
                                outRect.left = spaceBetween / 2
                            }
                            outRect.top = spaceBetween / 2
                            outRect.bottom = spaceBetween / 2
                        }
                    })
                    GridLayoutManager(root.context, spanCount, GridLayoutManager.VERTICAL, false).apply {
                        recycleChildrenOnDetach = true
                    }
                }
                musicAdapter.apply {
                    submitList(item.items)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMusicVH {
        return HomeMusicVH(
            LayoutSquareCellMusicListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeMusicVH, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {

        const val MAGIC_COL_PER_WIDTH_SCREEN_HORIZONTAL = 3

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MusicListUI>() {
            override fun areItemsTheSame(oldItem: MusicListUI, newItem: MusicListUI): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MusicListUI, newItem: MusicListUI): Boolean =
                oldItem == newItem
        }
    }
}