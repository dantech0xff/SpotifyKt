package com.creative.spotifykt.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.creative.spotifykt.R
import com.creative.spotifykt.data.model.local.SwitchUI
import com.creative.spotifykt.data.model.local.TextLabel

@BindingAdapter("bindImageUrl")
fun bindImageUrl(view: ImageView, imageUrl: String?) {
    if (imageUrl.isNullOrBlank()) {
        view.visibility = View.GONE
        return
    }
    view.visibility = View.VISIBLE

    Glide.with(view.context)
        .load(imageUrl)
        .apply(
            RequestOptions()
                .centerCrop()
                .downsample(DownsampleStrategy.AT_MOST)
        )
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.ic_launcher_foreground)
        .into(view)
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

            else -> textView.setTextColor(
                ResourcesCompat.getColor(
                    textView.context.resources,
                    R.color.xds_color_font_secondary, null
                )
            )
        }
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