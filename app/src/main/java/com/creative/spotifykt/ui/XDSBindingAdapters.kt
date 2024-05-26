package com.creative.spotifykt.ui

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.creative.spotifykt.R
import com.creative.spotifykt.data.model.local.SwitchUI
import com.creative.spotifykt.data.model.local.TextAttr
import com.creative.spotifykt.data.model.local.TextLabel
import com.creative.spotifykt.data.model.local.TextStyle

@BindingAdapter("bindImageUrl")
fun bindImageUrl(view: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrBlank()) {
        view.visibility = View.GONE
        return
    }
    view.visibility = View.VISIBLE

//    Glide.with(view.context)
//        .load(imageUrl)
//        .apply(
//            RequestOptions()
//                .centerCrop()
//                .downsample(DownsampleStrategy.AT_MOST)
//        )
//        .transition(DrawableTransitionOptions.withCrossFade())
//        .error(R.drawable.ic_launcher_foreground)
//        .into(view)
}

@BindingAdapter("bindTextLabel")
fun bindTextLabel(textView: TextView, textLabel: TextLabel?) {
    if (textLabel == null || textLabel.text.isNullOrBlank()) {
        textView.visibility = View.GONE
        return
    }
    textView.visibility = View.VISIBLE
    textLabel.let {
        textView.text = textLabel.text
        when (textLabel.colorStyle) {
            "PRIMARY" -> textView.setTextColor(
                ResourcesCompat.getColor(
                    textView.context.resources,
                    R.color.xds_color_font_primary, null
                )
            )
            "SUCCESS" -> {
                textView.setTextColor(
                    ResourcesCompat.getColor(
                        textView.context.resources,
                        R.color.xds_color_font_success, null
                    )
                )
            }
            "ERROR", "DANGER" -> {
                textView.setTextColor(
                    ResourcesCompat.getColor(
                        textView.context.resources,
                        R.color.xds_color_font_error, null
                    )
                )
            }
            "LINK" -> {
                textView.setTextColor(
                    ResourcesCompat.getColor(
                        textView.context.resources,
                        R.color.xds_color_font_link, null
                    )
                )
            }
            "PRIMARY_INVERSE" -> {
                textView.setTextColor(
                    ResourcesCompat.getColor(
                        textView.context.resources,
                        R.color.xds_color_font_primary_inverse, null
                    )
                )
            }
            else -> textView.setTextColor(
                ResourcesCompat.getColor(
                    textView.context.resources,
                    R.color.xds_color_font_secondary, null
                )
            )
        }
    }
}

@BindingAdapter("bindBgMobileData")
fun bindBgMobileData(view: ViewGroup, isSelected: Boolean?) {
    if (isSelected == true) {
        view.setBackgroundResource(R.drawable.bg_mobile_data_selected)
    } else {
        view.setBackgroundResource(R.drawable.bg_mobile_data_in_selected)
    }
}


fun String.hexToColor(): Int {
    // Remove the "#" if it's present
    val hex = if (this.startsWith("#")) this.substring(1) else this

    // Parse the hex string to an integer
    val color = hex.toLong(16).toInt()

    // Extract the individual color components (R, G, B)
    val red = color shr 16 and 0xFF
    val green = color shr 8 and 0xFF
    val blue = color and 0xFF

    // Create the RGB color by combining the components
    return android.graphics.Color.rgb(red, green, blue)
}


@BindingAdapter("bindCardViewBackground")
fun bindCardViewBackground(view: CardView, color: String?) {
    color?.let {
        // Color Int from Hex
        view.setCardBackgroundColor(color.hexToColor())
    }
}

@BindingAdapter("bindVisibleOrGone")
fun bindVisibleOrGone(view: View, isVisible: Boolean?) {
    if (isVisible == null) {
        view.visibility = View.GONE
        return
    }
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("bindSwitchUI")
fun bindSwitchUI(view: SwitchCompat, switchUI: SwitchUI?) {
    if (switchUI == null) {
        view.visibility = View.GONE
        return
    }
    view.visibility = View.VISIBLE
    view.apply {
        isChecked = switchUI.isOn ?: false
    }
}

@BindingAdapter("bindPercentConstraint")
fun bindPercentConstraint(view: View, percent: Float?) {
    if (percent == null) {
        view.visibility = View.GONE
        return
    }
    view.visibility = View.VISIBLE
    (view.layoutParams as? ConstraintLayout.LayoutParams)?.matchConstraintPercentWidth = (percent / 100F)
}

@BindingAdapter("bindTextAttr")
fun bindTextAttr(textView: TextView, textAttr: TextAttr?) {
    if (textAttr != null) {
        textView.visibility = View.VISIBLE
        textView.text = SpannableString(textAttr.text).apply {
            setSpan(
                android.text.style.StyleSpan(
                    when (textAttr.textStyle) {
                        TextStyle.REGULAR.value -> android.graphics.Typeface.NORMAL
                        TextStyle.BOLD.value -> android.graphics.Typeface.BOLD
                        else -> android.graphics.Typeface.NORMAL
                    }
                ),
                0,
                textAttr.text?.length ?: 0,
                android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    } else {
        textView.visibility = View.GONE
    }
}

@BindingAdapter("bindListTextAttr")
fun bindListTextAttr(textView: TextView, listTextAttr: List<TextAttr?>?) {
    val listAttr = listTextAttr?.filterNotNull()
    if (listAttr.isNullOrEmpty()) {
        textView.visibility = View.GONE
    } else {
        textView.visibility = View.VISIBLE
        val spn = SpannableStringBuilder("")
        listAttr.forEach {
            spn.append(
                SpannableStringBuilder(it.text).apply {
                    setSpan(
                        android.text.style.StyleSpan(
                            when (it.textStyle) {
                                TextStyle.REGULAR.value -> android.graphics.Typeface.NORMAL
                                TextStyle.BOLD.value -> android.graphics.Typeface.BOLD
                                else -> android.graphics.Typeface.NORMAL
                            }
                        ),
                        0,
                        it.text?.length ?: 0,
                        android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            )
        }
        textView.text = spn
    }
}
