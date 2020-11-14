package com.javnez.marvel.core

import android.widget.ImageView
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