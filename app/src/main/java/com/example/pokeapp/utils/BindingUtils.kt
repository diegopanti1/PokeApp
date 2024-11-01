package com.example.pokeapp.utils

import android.annotation.SuppressLint
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokeapp.R
import com.google.android.material.imageview.ShapeableImageView

@SuppressLint("CheckResult")
@BindingAdapter("loadImageUrl")
fun loadImage(view: ShapeableImageView, url: String?) {
    val requestOptions = RequestOptions()
    requestOptions.placeholder(R.drawable.ic_load)
    requestOptions.error(R.drawable.ic_failure)
    requestOptions.fitCenter()
    Glide
        .with(view.context)
        .setDefaultRequestOptions(requestOptions)
        .load(url)
        .into(view)
}