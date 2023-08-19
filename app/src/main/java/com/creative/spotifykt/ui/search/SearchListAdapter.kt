package com.creative.spotifykt.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.databinding.ItemSearchTopicBinding
import com.creative.spotifykt.data.model.local.MusicSearchTopic
import com.creative.spotifykt.ui.IDeeplinkHandler

class SearchListAdapter(
    private val handleDeeplink: IDeeplinkHandler? = null
) : ListAdapter<MusicSearchTopic, SearchListAdapter.SearchTopicVH>(DIFF_CALLBACK) {
    inner class SearchTopicVH(val viewBinding: ItemSearchTopicBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: MusicSearchTopic) {
            viewBinding.data = item
            viewBinding.deeplinkClick = handleDeeplink
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MusicSearchTopic>() {
            override fun areItemsTheSame(oldItem: MusicSearchTopic, newItem: MusicSearchTopic): Boolean =
                oldItem.deeplink == newItem.deeplink

            override fun areContentsTheSame(oldItem: MusicSearchTopic, newItem: MusicSearchTopic): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTopicVH {
        return SearchTopicVH(
            ItemSearchTopicBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchTopicVH, position: Int) {
        holder.bind(getItem(position))
    }
}