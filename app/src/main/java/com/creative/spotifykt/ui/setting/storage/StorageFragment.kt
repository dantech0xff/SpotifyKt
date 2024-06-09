package com.creative.spotifykt.ui.setting.storage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.creative.spotifykt.R
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.data.model.local.AppBarUI
import com.creative.spotifykt.data.model.local.ColorStyle
import com.creative.spotifykt.data.model.local.TextLabel
import com.creative.spotifykt.databinding.StorageFragmentBinding
import com.creative.spotifykt.ui.IAppBarHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StorageFragment : BaseFragment<StorageFragmentBinding>() {

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        StorageFragmentBinding.inflate(inflater, container, false)

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            settingToolbar.data = AppBarUI(title = TextLabel(getString(R.string.storage), ColorStyle.PRIMARY.value))
            settingToolbar.appBarHandler = object : IAppBarHandler {
                override fun handleBack() {
                    findNavController().navigateUp()
                }
            }
        }
    }
}