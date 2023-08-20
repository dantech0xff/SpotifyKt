package com.creative.spotifykt.ui.search.result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.creative.spotifykt.databinding.ItemMusicRowBinding
import com.creative.spotifykt.databinding.SearchResultAdditionalBinding
import com.creative.spotifykt.databinding.SearchResultFeatureListBinding
import com.creative.spotifykt.data.model.local.SearchResult
import com.creative.spotifykt.data.model.local.SearchResultText
import com.creative.spotifykt.data.model.local.SearchResultType
import com.creative.spotifykt.ui.IDeeplinkHandler
import com.creative.spotifykt.ui.home.MusicListAdapter

class SearchResultListAdapter(
    private val deeplinkHandler: IDeeplinkHandler? = null,
) : ListAdapter<SearchResult, ViewHolder>(DIFF_CALLBACK) {

    inner class SearchResultVH(val viewBinding: ViewBinding) : ViewHolder(viewBinding.root) {

        fun bind(resultData: SearchResult) {
            /*when (resultData.type) {
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
            }*/
        }
    }

    inner class SearchResultTextViewHolder(val viewBinding: SearchResultAdditionalBinding)
        : ViewHolder(viewBinding.root) {
        fun bind(data: SearchResultText?) {
            viewBinding.data = data
            viewBinding.deeplinkHandler = deeplinkHandler
        }
    }

    companion object {
        const val ROW_TYPE_TEXT = 1
        const val ROW_TYPE_MUSIC = 2
        const val ROW_TYPE_FEATURE_LIST = 3

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchResult>() {
            override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ROW_TYPE_TEXT -> SearchResultTextViewHolder(
                SearchResultAdditionalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )

            else -> object : ViewHolder(View(parent.context)) {}
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ROW_TYPE_TEXT -> (holder as? SearchResultTextViewHolder)?.bind(getItem(position).searchResultText)
        }
        (holder as? SearchResultVH)?.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            SearchResultType.TEXT.value -> ROW_TYPE_TEXT
            SearchResultType.MUSIC_ROW.value -> ROW_TYPE_MUSIC
            SearchResultType.FEATURE_LIST.value -> ROW_TYPE_FEATURE_LIST
            else -> super.getItemViewType(position)
        }
    }
}