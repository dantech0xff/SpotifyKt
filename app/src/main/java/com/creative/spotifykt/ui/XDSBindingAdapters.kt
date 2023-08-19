package com.creative.spotifykt.ui

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.creative.spotifykt.R
import com.creative.spotifykt.data.model.local.TextLabel

@BindingAdapter("bindImageUrl")
fun bindImageUrl(view: ImageView, imageUrl: String?) {
    if (imageUrl == null) {
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
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_foreground)
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                return false
            }

        })
        .into(view)
}

@BindingAdapter("bindTextLabel")
fun bindTextLabel(textView: TextView, textLabel: TextLabel?) {
    if (textLabel == null) {
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