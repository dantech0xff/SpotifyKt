package com.creative.spotifykt.ui.setting.mobiledata

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.creative.spotifykt.data.model.local.DataLimitCellUI
import com.creative.spotifykt.databinding.ItemMobileDataBinding

/**
 * Created by dan on 04/09/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

class LimitMobileDataAdapter(
    val setSelectedPosition: (Int) -> Unit
) : Adapter<LimitMobileDataAdapter.LimitMobileDataVH>() {

    private val listData = mutableListOf<DataLimitCellUI>()

    inner class LimitMobileDataVH(
        val binding: ItemMobileDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataLimitCellUI?, position: Int) {
            binding.apply {
                root.setOnClickListener {
                    setSelectedPosition.invoke(position)
                }

                isSelected = item?.isSelected
                textLabel = item?.limitText
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

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: LimitMobileDataVH, position: Int) {
        holder.bind(listData.getOrNull(position), position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<DataLimitCellUI>) {
        listData.clear()
        listData.addAll(newData)
        notifyDataSetChanged()
    }
}