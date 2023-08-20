package com.creative.spotifykt.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.databinding.ItemSearchTopicBinding
import com.creative.spotifykt.data.model.local.SearchTopic
import com.creative.spotifykt.ui.IDeeplinkHandler

class SearchListAdapter(
    private val handleDeeplink: IDeeplinkHandler? = null
) : ListAdapter<SearchTopic, SearchListAdapter.SearchTopicVH>(DIFF_CALLBACK) {
    inner class SearchTopicVH(val viewBinding: ItemSearchTopicBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: SearchTopic) {
            viewBinding.data = item
            viewBinding.deeplinkClick = handleDeeplink
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchTopic>() {
            override fun areItemsTheSame(oldItem: SearchTopic, newItem: SearchTopic): Boolean =
                oldItem.deeplink == newItem.deeplink

            override fun areContentsTheSame(oldItem: SearchTopic, newItem: SearchTopic): Boolean =
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