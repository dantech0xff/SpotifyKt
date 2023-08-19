package com.creative.spotifykt.base.ui

import androidx.viewbinding.ViewBinding
import com.creative.spotifykt.base.data.BaseModelData

abstract class BaseViewBindingWrapper<VB: ViewBinding, MD: BaseModelData>(val viewBinding: VB) {
    lateinit var data: MD

    fun bindData(itemModelData: MD) {
        data = itemModelData
        internalBindData(data)
    }

    abstract fun internalBindData(itemModelData: MD)

    fun bindData(itemModelData: MD, handleOnClick: (itemModelData: MD) -> Any) {
        bindData(itemModelData)
        viewBinding.root.setOnClickListener {
            handleOnClick(data)
        }
    }
}