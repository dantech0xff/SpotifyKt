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
import com.creative.spotifykt.core.log
import com.creative.spotifykt.databinding.LayoutSquareCellMusicListBinding
import com.creative.spotifykt.data.model.local.LayoutOrientation
import com.creative.spotifykt.data.model.local.MusicListUI
import com.creative.spotifykt.ui.IDeeplinkHandler

class HomeListAdapter(
    private val handleDeeplink: IDeeplinkHandler? = null
) : ListAdapter<MusicListUI, HomeListAdapter.HomeMusicVH>(DIFF_CALLBACK) {

    init {
        setHasStableIds(true)
    }

    inner class HomeMusicVH(private val itemViewBinding: LayoutSquareCellMusicListBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        private val musicAdapter: MusicListAdapter by lazy {
            MusicListAdapter(handleDeeplink = handleDeeplink)
        }

        init {
            itemViewBinding.apply {
                recyclerView.adapter = musicAdapter
            }
        }

        fun bind(item: MusicListUI) {
            itemViewBinding.apply {
                data = item
                handleActionIconClick = handleDeeplink

                val spanCount = item.layout?.spanCount ?: 1
                val orientation = item.layout?.orientation ?: LayoutOrientation.VERTICAL.value

                val screenWidth: Int = root.context.resources.displayMetrics.widthPixels
                val spaceLeftRight = root.context.resources.getDimensionPixelSize(R.dimen.xds_space_l)
                val spaceBetween = root.context.resources.getDimensionPixelSize(R.dimen.xds_space_s)

                if (recyclerView.layoutManager == null) {
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
                        if (spanCount > 1) {
                            GridLayoutManager(root.context, spanCount, GridLayoutManager.HORIZONTAL, false)
                        } else {
                            LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
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
                        if (spanCount == 1) {
                            LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false)
                        } else {
                            GridLayoutManager(root.context, spanCount, GridLayoutManager.VERTICAL, false)
                        }
                    }
                }

                musicAdapter.apply {
                    submitList(item.items)
                }

                executePendingBindings()
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
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
        log("HomeListAdapter onBindViewHolder: $position")
    }

    override fun onViewRecycled(holder: HomeMusicVH) {
        super.onViewRecycled(holder)
        log("HomeListAdapter onViewRecycled: ${holder.adapterPosition}")
    }

    override fun onViewAttachedToWindow(holder: HomeMusicVH) {
        super.onViewAttachedToWindow(holder)
        log("HomeListAdapter onViewAttachedToWindow: ${holder.adapterPosition}")
    }

    override fun onViewDetachedFromWindow(holder: HomeMusicVH) {
        super.onViewDetachedFromWindow(holder)
        log("HomeListAdapter onViewDetachedFromWindow: ${holder.adapterPosition}")
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