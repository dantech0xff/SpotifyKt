package com.creative.spotifykt.ui.setting.mobiledata

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.creative.spotifykt.R
import com.creative.spotifykt.core.dpToPixelsInt
import com.creative.spotifykt.databinding.MobileDataFragmentBinding
import com.creative.spotifykt.di.component.FragmentComponent
import com.creative.spotifykt.core.ui.BaseFragment
import com.creative.spotifykt.data.model.local.AppBarUI
import com.creative.spotifykt.data.model.local.ColorStyle
import com.creative.spotifykt.data.model.local.TextLabel
import com.creative.spotifykt.ui.IAppBarHandler

class MobileDataFragment : BaseFragment<MobileDataFragmentBinding, MobileDataViewModel>() {

    private val limitMobileDataAdapter by lazy {
        LimitMobileDataAdapter { viewModel.setSelectedPosition(it) }
    }

    override fun provideViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        MobileDataFragmentBinding.inflate(inflater, container, false)

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        viewBinding?.apply {
            settingToolbar.data = AppBarUI(
                title = TextLabel(
                    text = getString(R.string.mobile_data), colorStyle = ColorStyle.PRIMARY.value
                )
            )
            settingToolbar.appBarHandler = object : IAppBarHandler {
                override fun handleBack() {
                    findNavController().popBackStack()
                }
            }
            listLimitData.adapter = limitMobileDataAdapter
            listLimitData.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            listLimitData.addItemDecoration(object : ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    super.getItemOffsets(outRect, view, parent, state)
                    (view as? ViewGroup)?.apply {
                        layoutParams = layoutParams.apply {
                            width = context.dpToPixelsInt(86)
                            height = ViewGroup.LayoutParams.WRAP_CONTENT
                        }
                    }
                    outRect.top = root.context.dpToPixelsInt(10)
                    outRect.bottom = root.context.dpToPixelsInt(10)
                    outRect.left = root.context.dpToPixelsInt(10)
                    outRect.right = root.context.dpToPixelsInt(10)
                }
            })
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.settingMobileData.observe(viewLifecycleOwner) {
            when (it) {
                is SettingMobileDataState.Success -> {
                    viewBinding?.apply {
                        data = it.data
                        executePendingBindings()
                    }
                    limitMobileDataAdapter.setData(it.data.listLimit ?: listOf())
                }

                else -> {}
            }
        }
    }
}