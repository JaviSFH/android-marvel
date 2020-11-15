package com.javnez.marvel.core

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.javnez.marvel.R

@BindingAdapter("app:imageUrl")
fun setImageDrawable(imageView: ImageView, url: String?) {

    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.image_placeholder)
        .error(R.drawable.image_placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
}

@BindingAdapter("android:htmlText")
fun setHtmlTextValue(textView: TextView, htmlText: String?) {
    if (htmlText.isNullOrEmpty()) return
    val result = if (VERSION.SDK_INT >= VERSION_CODES.N) {
        Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(htmlText)
    }
    textView.text = result
}