package com.creative.spotifykt.ui.search.result

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.creative.spotifykt.R
import com.creative.spotifykt.databinding.ItemMusicRowBinding
import com.creative.spotifykt.databinding.SearchResultAdditionalBinding
import com.creative.spotifykt.databinding.SearchResultFeatureListBinding
import com.creative.spotifykt.data.model.local.SearchResult
import com.creative.spotifykt.data.model.local.SearchResultFeatureList
import com.creative.spotifykt.data.model.local.SearchResultMusicRow
import com.creative.spotifykt.data.model.local.SearchResultText
import com.creative.spotifykt.data.model.local.SearchResultType
import com.creative.spotifykt.ui.IDeeplinkHandler
import com.creative.spotifykt.ui.home.HomeListAdapter
import com.creative.spotifykt.ui.home.MusicListAdapter

class SearchResultListAdapter(
    private val deeplinkHandler: IDeeplinkHandler? = null,
) : ListAdapter<SearchResult, ViewHolder>(DIFF_CALLBACK) {

    inner class SearchResultTextViewHolder(val viewBinding: SearchResultAdditionalBinding) : ViewHolder(viewBinding.root) {
        fun bind(data: SearchResultText?) {
            viewBinding.data = data
            viewBinding.deeplinkHandler = deeplinkHandler
        }
    }

    inner class SearchResultMusicRowViewHolder(val viewBinding: ItemMusicRowBinding) : ViewHolder(viewBinding.root) {
        fun bind(data: SearchResultMusicRow?) {
            viewBinding.data = data
            viewBinding.deeplinkHandler = deeplinkHandler
        }
    }

    inner class SearchResultFeatureListViewHolder(val viewBinding: SearchResultFeatureListBinding)
        : ViewHolder(viewBinding.root) {
        private val musicListAdapter: MusicListAdapter by lazy { MusicListAdapter(deeplinkHandler) }

        init {
            viewBinding.listFeatureMusic.apply {
                val screenWidth: Int = context.resources.displayMetrics.widthPixels
                val spaceLeftRight = context.resources.getDimensionPixelSize(R.dimen.xds_space_l)
                val spaceBetween = context.resources.getDimensionPixelSize(R.dimen.xds_space_s)

                layoutManager = LinearLayoutManager(context).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
                adapter = musicListAdapter
                addItemDecoration(object : ItemDecoration() {
                    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                        super.getItemOffsets(outRect, view, parent, state)
                        (view as? ViewGroup)?.apply {
                            layoutParams = layoutParams.apply {
                                width = (screenWidth - spaceLeftRight) / 2
                                height = ViewGroup.LayoutParams.WRAP_CONTENT
                            }
                        }
                        val index = parent.getChildAdapterPosition(view)
                        if (index == 0) {
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
            }
        }

        fun bind(data: SearchResultFeatureList?) {
            viewBinding.data = data
            musicListAdapter.submitList(data?.featureList)
        }
    }

    companion object {
        const val ROW_TYPE_TEXT = 1
        const val ROW_TYPE_MUSIC = 2
        const val ROW_TYPE_FEATURE_LIST = 3

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SearchResult>() {
            override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean =
                oldItem.toString() == newItem.toString()

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

            ROW_TYPE_MUSIC -> SearchResultMusicRowViewHolder(
                ItemMusicRowBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
            )

            ROW_TYPE_FEATURE_LIST -> SearchResultFeatureListViewHolder(
                SearchResultFeatureListBinding.inflate(
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
            ROW_TYPE_MUSIC -> (holder as? SearchResultMusicRowViewHolder)?.bind(getItem(position).searchResultMusicRow)
            ROW_TYPE_FEATURE_LIST -> (holder as? SearchResultFeatureListViewHolder)?.bind(getItem(position).searchResultFeatureList)
            else -> {}
        }
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