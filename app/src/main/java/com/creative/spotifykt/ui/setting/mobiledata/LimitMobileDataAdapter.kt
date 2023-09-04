package com.creative.spotifykt.ui.setting.mobiledata

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.data.model.local.DataLimitCellUI
import com.creative.spotifykt.databinding.ItemMobileDataBinding

/**
 * Created by dan on 04/09/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

class LimitMobileDataAdapter(
    val getSelectedPosition: (()-> Int),
    val setSelectedPosition: ((Int)-> Unit)
) : ListAdapter<DataLimitCellUI, LimitMobileDataAdapter.LimitMobileDataVH>(DIFF_UTILS) {
    companion object {
        val DIFF_UTILS = object : DiffUtil.ItemCallback<DataLimitCellUI>() {
            override fun areItemsTheSame(oldItem: DataLimitCellUI, newItem: DataLimitCellUI): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataLimitCellUI, newItem: DataLimitCellUI): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class LimitMobileDataVH(
        val binding: ItemMobileDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataLimitCellUI, position: Int) {
            binding.apply {
                isSelected = position == getSelectedPosition.invoke()
                textLabel = if (isSelected) {
                    item.activeText
                } else {
                    item.inactiveText
                }
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LimitMobileDataVH {
        return LimitMobileDataVH(
            ItemMobileDataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LimitMobileDataVH, position: Int) {
        holder.bind(getItem(position), position)
    }
}