package com.creative.spotifykt.spotifylite.ui.premium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.creative.spotifykt.spotifylite.data.PremiumItemData
import com.creative.spotifykt.databinding.PremiumFragmentBinding
import com.creative.spotifykt.spotifylite.di.component.FragmentComponent
import com.creative.spotifykt.base.ui.BaseFragment

class PremiumFragment : BaseFragment<PremiumFragmentBinding, PremiumViewModel>() {
    private lateinit var recyclerPremiumInfo: RecyclerView
    private val infoAdapter: PremiumInfoAdapter by lazy { PremiumInfoAdapter() }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?): PremiumFragmentBinding =
        PremiumFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        recyclerPremiumInfo = requireViewBinding().listDiffInfo

        recyclerPremiumInfo.adapter = infoAdapter
        recyclerPremiumInfo.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }

        infoAdapter.differ.submitList(mutableListOf<PremiumItemData?>().apply {
            add(PremiumItemData("Add breaks", "Ad free music"))
            add(PremiumItemData("Play in shuffle", "Play any song"))
            add(PremiumItemData("6 skips per hour", "Unlimited skips"))
            add(PremiumItemData("Streaming only", "Offline listening"))
            add(PremiumItemData("Basic quality", "High audio quality"))
        })
    }
}