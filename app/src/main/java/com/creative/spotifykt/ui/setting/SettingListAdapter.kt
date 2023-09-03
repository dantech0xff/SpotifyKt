package com.creative.spotifykt.ui.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.data.model.local.SettingRowUI
import com.creative.spotifykt.databinding.ItemSettingRowBinding

/**
 * Created by dan on 03/09/2023
 *
 * Copyright © 2023 1010 Creative. All rights reserved.
 */

class SettingListAdapter : ListAdapter<SettingRowUI, SettingListAdapter.SettingRowViewHolder>(DIFF_UTILS) {

    companion object {
        val DIFF_UTILS = object : DiffUtil.ItemCallback<SettingRowUI>() {
            override fun areItemsTheSame(oldItem: SettingRowUI, newItem: SettingRowUI): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SettingRowUI, newItem: SettingRowUI): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class SettingRowViewHolder(private val binding: ItemSettingRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(settingRowUI: SettingRowUI) {
            binding.apply {
                data = settingRowUI
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingRowViewHolder {
        return SettingRowViewHolder(
            ItemSettingRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SettingRowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}