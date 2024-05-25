package com.creative.spotifykt.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.databinding.ItemMusicSquareBinding
import com.creative.spotifykt.data.model.local.MusicSquareUI
import com.creative.spotifykt.ui.IDeeplinkHandler

class MusicListAdapter(
    private val handleDeeplink: IDeeplinkHandler? = null
) : ListAdapter<MusicSquareUI, MusicListAdapter.MusicVH>(DIFF_CALLBACK) {

    inner class MusicVH(private val itemViewBinding: ItemMusicSquareBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(item: MusicSquareUI) {
            itemViewBinding.apply {
                data = item
                deeplinkHandler = handleDeeplink
                executePendingBindings()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MusicSquareUI>() {
            override fun areItemsTheSame(oldItem: MusicSquareUI, newItem: MusicSquareUI): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MusicSquareUI, newItem: MusicSquareUI): Boolean =
                oldItem == newItem

            override fun getChangePayload(oldItem: MusicSquareUI, newItem: MusicSquareUI): Any? {
                return newItem
            }
        }
        const val ITEM_VIEW_TYPE_SQUARE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicVH {
        return MusicVH(ItemMusicSquareBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MusicVH, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is MusicSquareUI) ITEM_VIEW_TYPE_SQUARE else super.getItemViewType(position)
    }
}