package com.example.ecomapp.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.ecomapp.R
import com.google.android.material.imageview.ShapeableImageView
import kotlin.coroutines.coroutineContext

@BindingAdapter("imageUrl")
fun bindImage(imgView: ShapeableImageView, imgUrl: String?) {
    Glide.with(imgView.context).load(imgUrl).apply ( RequestOptions()
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_broken_image)).into(imgView)
    }
