package com.creative.spotifykt.ui.favorite.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.data.model.local.FavoriteMusicRow
import com.creative.spotifykt.data.model.local.toSearchResultMusicRow
import com.creative.spotifykt.databinding.ItemMusicRowBinding
import com.creative.spotifykt.ui.IDeeplinkHandler

class ListFavAdapter(
    private val deeplinkHandler: IDeeplinkHandler? = null
) : RecyclerView.Adapter<ListFavAdapter.FavMusicRowVH>() {
    inner class FavMusicRowVH(private val viewBinding: ItemMusicRowBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: FavoriteMusicRow) {
            viewBinding.deeplinkHandler = deeplinkHandler
            viewBinding.data = item.toSearchResultMusicRow()
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<FavoriteMusicRow>() {
        override fun areItemsTheSame(oldItem: FavoriteMusicRow, newItem: FavoriteMusicRow): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FavoriteMusicRow, newItem: FavoriteMusicRow): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMusicRowVH {
        return FavMusicRowVH(ItemMusicRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<FavoriteMusicRow>?) {
        differ.submitList(list)
    }

    override fun onBindViewHolder(holder: FavMusicRowVH, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }
}