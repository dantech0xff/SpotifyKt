package com.creative.spotifykt.ui.setting

import android.view.View
import com.creative.spotifykt.data.model.local.SettingRowItem
import com.creative.spotifykt.data.model.local.SettingRowType
import com.creative.spotifykt.databinding.ItemSettingRowBinding
import com.creative.spotifykt.core.ui.BaseViewBindingWrapper

class SettingRowViewWrapper(viewBinding: ItemSettingRowBinding) : BaseViewBindingWrapper<ItemSettingRowBinding, SettingRowItem>(viewBinding) {

    override fun internalBindData(itemModelData: SettingRowItem) {
        viewBinding.apply {
            settingIcon.visibility = View.GONE
            titleCenter.visibility = View.GONE
            title.visibility = View.GONE
            subTitle.visibility = View.GONE
            separateLine.visibility = View.GONE

            when (itemModelData.rowType) {
                SettingRowType.ROW_SECTION_SEPARATE -> {
                    separateLine.visibility = View.VISIBLE
                }
                else -> {
                    if (itemModelData.iconRes != 0) {
                        settingIcon.apply {
                            visibility = View.VISIBLE
                            setImageResource(itemModelData.iconRes)
                        }
                    } else {
                        settingIcon.visibility = View.GONE
                    }
                    if (itemModelData.subTitle.isEmpty()) {
                        titleCenter.apply {
                            text = itemModelData.title
                            visibility = View.VISIBLE
                        }
                        subTitle.visibility = View.GONE
                        title.visibility = View.GONE
                    } else {
                        title.apply {
                            visibility = View.VISIBLE
                            text = itemModelData.title
                        }
                        subTitle.apply {
                            visibility = View.VISIBLE
                            text = itemModelData.subTitle
                        }
                        titleCenter.visibility = View.GONE
                    }
                }
            }
        }
    }

    fun setSlide(slide: Float) {
        if (data.rowType == SettingRowType.ROW_SETTING_SLIDER) {
            viewBinding.settingSlider.apply {
                visibility = View.VISIBLE
                value = slide
            }
        } else {
            viewBinding.settingSlider.visibility = View.GONE
        }
    }

    fun setChecked(check: Boolean) {
        viewBinding.settingCheck.visibility = if (check && data.rowType == SettingRowType.ROW_SETTING_CHECKBOX)
            View.VISIBLE
        else
            View.GONE

        viewBinding.settingSwitch.apply {
            if (data.rowType == SettingRowType.ROW_SETTING_SWITCH) {
                visibility = View.VISIBLE
                isChecked = check
            } else {
                visibility = View.GONE
            }
        }
    }
}