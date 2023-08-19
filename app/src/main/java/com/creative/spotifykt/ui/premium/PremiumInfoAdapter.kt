package com.creative.spotifykt.ui.premium

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.databinding.ItemPremiumInfoBinding
import com.creative.spotifykt.data.model.local.PremiumItemData

class PremiumInfoAdapter : RecyclerView.Adapter<PremiumInfoAdapter.PremiumInfoVH>() {
    inner class PremiumInfoVH(val viewBinding: ItemPremiumInfoBinding) : RecyclerView.ViewHolder(viewBinding.root)


    private val differCallback = object : DiffUtil.ItemCallback<PremiumItemData>() {
        override fun areItemsTheSame(oldItem: PremiumItemData, newItem: PremiumItemData): Boolean {
            return oldItem.freeDesc == newItem.freeDesc && oldItem.premiumDesc == newItem.premiumDesc
        }

        override fun areContentsTheSame(oldItem: PremiumItemData, newItem: PremiumItemData): Boolean {
            return oldItem.freeDesc == newItem.freeDesc && oldItem.premiumDesc == newItem.premiumDesc
        }
    }
    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PremiumInfoVH {
        return PremiumInfoVH(ItemPremiumInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PremiumInfoVH, position: Int) {
        val item = differ.currentList[position]
        holder.viewBinding.apply {
            root.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
            freeDesc.text = item.freeDesc
            premiumDesc.text = item.premiumDesc
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((PremiumItemData) -> Unit)? = null
    fun setOnItemClickListener(listener: (PremiumItemData) -> Unit) {
        onItemClickListener = listener
    }
}