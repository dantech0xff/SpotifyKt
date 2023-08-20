package com.creative.spotifykt.ui.favorite.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.databinding.ItemMusicRowBinding
import com.creative.spotifykt.data.model.local.FavMusicData

class ListFavAdapter : RecyclerView.Adapter<ListFavAdapter.FavMusicRowVH>() {
    inner class FavMusicRowVH(val viewBinding: ItemMusicRowBinding) : RecyclerView.ViewHolder(viewBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<FavMusicData>() {
        override fun areItemsTheSame(oldItem: FavMusicData, newItem: FavMusicData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavMusicData, newItem: FavMusicData): Boolean {
            return oldItem.title == newItem.title
                    && oldItem.subTitle == newItem.subTitle && oldItem.id == newItem.id
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMusicRowVH {
        return FavMusicRowVH(ItemMusicRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FavMusicRowVH, position: Int) {
        val item = differ.currentList[position]
        holder.viewBinding.apply {
            root.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }

            if (item.subTitle.isNotEmpty()) {
                title.text = item.title
                title.visibility = View.VISIBLE
                subTitle.text = item.subTitle
                subTitle.visibility = View.VISIBLE
//                titleCenter.visibility = View.GONE
            } else {
//                titleCenter.apply {
//                    visibility = View.VISIBLE
//                    text = item.title
//                }
                title.visibility = View.GONE
                subTitle.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((FavMusicData) -> Unit)? = null
    fun setOnItemClickListener(listener: (FavMusicData) -> Unit) {
        onItemClickListener = listener
    }
}