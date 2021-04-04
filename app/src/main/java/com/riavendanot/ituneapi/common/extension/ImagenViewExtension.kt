package com.riavendanot.ituneapi.common.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.riavendanot.ituneapi.R

fun ImageView.loadImg(url: String) {
    Glide
            .with(this)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(this);
}