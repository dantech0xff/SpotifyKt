package com.creative.spotifykt.ui.search.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.creative.spotifykt.core.toast
import com.creative.spotifykt.databinding.ItemMusicRowBinding
import com.creative.spotifykt.databinding.SearchResultAdditionalBinding
import com.creative.spotifykt.databinding.SearchResultFeatureListBinding
import com.creative.spotifykt.data.model.local.MusicSquareUI
import com.creative.spotifykt.data.model.local.SearchResultData
import com.creative.spotifykt.data.model.local.SearchResultType
import com.creative.spotifykt.ui.home.MusicListAdapter

class SearchResultListAdapter : ListAdapter<SearchResultData, SearchResultListAdapter.SearchResultVH>(DIFF_CALLBACK) {

    inner class SearchResultVH(val viewBinding: ViewBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        private val musicAdapter: MusicListAdapter by lazy {
            MusicListAdapter()
        }

        private fun onMusicItemClick(musicItem: MusicSquareUI) {
            itemView.context.toast(musicItem.toString())
        }

        fun bind(resultData: SearchResultData) {
            when (resultData.type) {
                SearchResultType.MUSIC -> {
                    with(viewBinding as ItemMusicRowBinding) {
                        title.text = resultData.title
                        subTitle.text = resultData.subTitle
                    }
                }

                SearchResultType.FEATURE -> {
                    with(viewBinding as SearchResultFeatureListBinding) {
                        textTitle.text = resultData.title
                        listFeatureMusic.apply {
                            layoutManager = LinearLayoutManager(context).apply {
                                orientation = LinearLayoutManager.HORIZONTAL
                            }
                            adapter = musicAdapter.apply {
                                submitList(resultData.featureList)
                            }
                        }
                    }
                }

                SearchResultType.ADDITIONAL -> {
                    with(viewBinding as SearchResultAdditionalBinding) {
                        textTitle.text = resultData.title
                    }
                }
            }
        }
    }

    companion object {
        const val ROW_TYPE_MUSIC = 1
        const val ROW_TYPE_LIST_FEATURE = 2
        const val ROW_TYPE_ADDITIONAL = 3

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchResultData>() {
            override fun areItemsTheSame(oldItem: SearchResultData, newItem: SearchResultData): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: SearchResultData, newItem: SearchResultData): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultVH {
        return when (viewType) {
            ROW_TYPE_MUSIC -> SearchResultVH(
                ItemMusicRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )

            ROW_TYPE_LIST_FEATURE -> SearchResultVH(
                SearchResultFeatureListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )

            ROW_TYPE_ADDITIONAL -> SearchResultVH(
                SearchResultAdditionalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )

            else -> SearchResultVH(
                SearchResultAdditionalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: SearchResultVH, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            SearchResultType.MUSIC -> ROW_TYPE_MUSIC
            SearchResultType.FEATURE -> ROW_TYPE_LIST_FEATURE
            SearchResultType.ADDITIONAL -> ROW_TYPE_ADDITIONAL
        }
    }
}