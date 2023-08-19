package com.creative.spotifykt.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.databinding.ItemSearchTopicBinding
import com.creative.spotifykt.data.model.local.MusicTopic

class SearchListAdapter : ListAdapter<MusicTopic, SearchListAdapter.SearchTopicVH>(DIFF_CALLBACK) {
    inner class SearchTopicVH(val viewBinding: ItemSearchTopicBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: MusicTopic) {
            viewBinding.tvTopic.text = item.name
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MusicTopic>() {
            override fun areItemsTheSame(oldItem: MusicTopic, newItem: MusicTopic): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: MusicTopic, newItem: MusicTopic): Boolean =
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